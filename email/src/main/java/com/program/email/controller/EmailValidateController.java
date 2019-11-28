package com.program.email.controller;

import com.program.email.common.ApiException;
import com.program.email.common.ResponseBody;
import com.program.email.entity.EmailValidate;
import com.program.email.entity.User;
import com.program.email.service.EmailValidateService;
import com.program.email.service.UserService;
import com.program.email.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮箱校验控制层
 *
 * @author manzp0329
 */
@RestController
@RequestMapping(value = "/validate")
public class EmailValidateController {

    /**
     * 邮箱校验service
     */
    @Autowired
    private EmailValidateService emailValidateService;
    /**
     * 用户service
     */
    @Autowired
    private UserService userService;
    /**
     * 发送人 username
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送忘记密码邮件请求，每日申请次数不超过5次，每次申请间隔不低于1分钟
     *
     * @param email 邮箱地址
     * @param request HttpServletRequest
     * @return true or false
     */
    @ApiOperation(value = "发送忘记密码邮件", notes = "发送忘记密码邮件")
    @RequestMapping(value = "/sendValidationEmail", method = {RequestMethod.POST})
    public ResponseBody sendValidationEmail(@ApiParam("邮箱地址") @RequestParam("email") String email,
        HttpServletRequest request) throws ApiException {
        User user = userService.queryUsersByEmail(email);
        if (null == user) {
            return ResultUtil.getErrorResponse(UUID.randomUUID().toString(),
                20001, "该邮箱所属用户不存在");
        } else {
            if (emailValidateService.sendValidateLimitation(email, 500, 0)) {
                // 若允许重置密码，则在pm_validate表中插入一行数据，带有token
                String resetToken = UUID.randomUUID().toString();
                emailValidateService
                    .insertNewResetRecord(user, resetToken, "");
                // 设置邮件内容
                String appUrl = request.getScheme() + "://" + request.getServerName();
                SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
                passwordResetEmail.setFrom(from);
                passwordResetEmail.setTo(email);
                passwordResetEmail.setSubject("【Hello】忘记密码");
                passwordResetEmail.setText(
                    "您正在申请重置密码，请点击此链接重置密码: \n" + appUrl + "/validate/reset?token=" + resetToken);
                emailValidateService.sendPasswordResetEmail(passwordResetEmail);
                return ResultUtil.getSuccessResponse(UUID.randomUUID().toString());
            } else {
                return ResultUtil.getErrorResponse(UUID.randomUUID().toString(), 20002,
                    "操作过于频繁，请稍后再试！");
            }
        }
    }

    /**
     * 将url的token和数据库里的token匹配，成功后便可修改密码，token有效期为60分钟
     *
     * @param token token
     * @param password 密码
     * @param confirmPassword 密码确认
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseBody resetPassword(@ApiParam("token") @RequestParam("token") String token,
        @ApiParam("密码") @RequestParam("password") String password,
        @ApiParam("密码确认") @RequestParam("confirmPassword") String confirmPassword) {
        // 通过token找到validate记录
        List<EmailValidate> validates = emailValidateService.findUserByResetToken(token);
        if (validates == null || validates.size() < 1) {
            return ResultUtil.getErrorResponse(UUID.randomUUID().toString(),
                20003, "该重置请求不存在");
        } else {
            EmailValidate validate = validates.get(0);
            if (emailValidateService
                .validateLimitation(validate.getEmail(), Long.MAX_VALUE, 60, token)) {
                Long userId = validate.getUserId();
                System.out.println("userId : " + userId);
                User user = userService.selectById(userId);
                if (password.equals(confirmPassword)) {
                    //sha256加密
                    String newPassword = new Sha256Hash(password, user.getSalt())
                        .toHex();
                    //更新密码
                    user.setPassword(newPassword);
                    //重置密码
                    userService.updateById(user);
                    return ResultUtil.getSuccessResponse(UUID.randomUUID().toString());
                } else {
                    return ResultUtil
                        .getErrorResponse(UUID.randomUUID().toString(), 20005, "确认密码和密码不一致，请重新输入");
                }
            } else {
                return ResultUtil
                    .getErrorResponse(UUID.randomUUID().toString(), 20006, "该链接失效");
            }
        }
    }
}