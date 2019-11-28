package com.program.user.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.program.user.annotation.CheckAuth;
import com.program.user.annotation.PassAuth;
import com.program.user.common.ApiException;
import com.program.user.common.Contents;
import com.program.user.entity.User;
import com.program.user.enums.LoginErrorCodeEnum;
import com.program.user.model.JWTPros;
import com.program.user.service.UserService;
import com.program.user.utils.GenerateIdUtil;
import com.program.user.utils.SystemContext;
import com.program.user.utils.SystemProperty;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger2.web.Swagger2Controller;

/**
 * @description: token 验证
 * @create: 2019-09-28 14:52
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private UserService userService;

    //@Value("${jwt.secret}")
    private String secret = JWTPros.secret;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object object) throws ApiException {
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        // 如果不是映射到方法直接通过 例如swagger
        HandlerMethod handlerMethod = (HandlerMethod) object;
        if (handlerMethod.getBean() instanceof ApiResourceController) {
            return true;
        }
        if (handlerMethod.getBean() instanceof Swagger2Controller) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        String reqId = GenerateIdUtil.nextUUID();
        logger.debug("{} ===> {} ===> 本次请求线程ID为: {}", "JWTInterceptor", "preHandle", reqId);
        // 检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassAuth.class)) {
            SystemContext.setSystemProperty(new SystemProperty(reqId, null, null));
            return true;
        }
        if (!method.isAnnotationPresent(CheckAuth.class)) {
            throw new ApiException(LoginErrorCodeEnum.PATH_ERROR.getCode(),
                LoginErrorCodeEnum.PATH_ERROR.getMessage());
        }
        String token = httpServletRequest.getHeader(Contents.TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            throw new ApiException(LoginErrorCodeEnum.TOKEN_NOT_EXIST.getCode(),
                LoginErrorCodeEnum.TOKEN_NOT_EXIST.getMessage());
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            logger.error("{} ===> {} ===> JWT验证失败: {}", "JWTInterceptor", "preHandle", e);
            throw new ApiException(LoginErrorCodeEnum.TOKEN_JWT_EXPIRE.getCode(),
                LoginErrorCodeEnum.TOKEN_JWT_EXPIRE.getMessage());
        }
        // 获取 token 中的 user id
        String userId = JWT.decode(token).getAudience().get(0);
        String userName = JWT.decode(token).getAudience().get(1);
        logger.info("{} ===> {} ===> 访问用户名称为: {}", "JWTInterceptor", "preHandle", userName);
        User user = userService.queryByUserName(userName);
        if (user == null) {
            throw new ApiException(LoginErrorCodeEnum.SYS_USER_NOT_FOUND.getCode(),
                LoginErrorCodeEnum.SYS_USER_NOT_FOUND.getMessage());
        }
        SystemContext
            .setSystemProperty(new SystemProperty(reqId, Long.valueOf(userId), userName));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Object object, Exception e) throws Exception {
        if (!(object instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        if (handlerMethod.getBean() instanceof ApiResourceController) {
            return;
        }
        if (handlerMethod.getBean() instanceof Swagger2Controller) {
            return;
        }
        logger.debug("{} ===> {} ===> 本次请求结束，线程ID为: {}", "JWTInterceptor", "preHandle",
            SystemContext.getReqId());
        SystemContext.cleanSystemProperty();
    }

}
