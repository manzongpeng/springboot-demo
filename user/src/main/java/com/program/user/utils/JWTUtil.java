package com.program.user.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.program.user.common.ApiException;
import com.program.user.enums.MathEnum;
import com.program.user.model.JWTPros;
import com.program.user.model.UserDTO;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: JWT service 实现
 * @create: 2019-09-28 16:24
 **/
@Component
public class JWTUtil {

    /**
     * 密钥自定义
     */
    //@Value("${jwt.secret}")
    private String secret = JWTPros.secret;

    //@Value("${spring.application.name}")
    private String applicationName = "user-web";

    private Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    public String generateToken(UserDTO userDTO) throws ApiException {
        Date now = new Date();
        DateTime endTime = DateUtil.offsetHour(now, MathEnum.FOUR.getNumber());
        // 12小时有效时间
        logger.info("{} ===> {} ===> 开始时间: {} 结束时间:{}", "JWTUtil", "generateToken",
            DateUtil.formatDateTime(now), DateUtil.formatDateTime(endTime));
        // 生成token
        String token = JWT.create().withIssuer(applicationName)
            .withAudience(userDTO.getId().toString(), userDTO.getUserName())
            // 签发时间
            .withIssuedAt(now).withExpiresAt(endTime).sign(Algorithm.HMAC256(secret));
        logger.info("{} ===> {} ===> 用户: {} 登陆生成Token:{}", "JWTUtil", "generateToken",
            userDTO.getUserName(), token);
        return token;
    }
}
