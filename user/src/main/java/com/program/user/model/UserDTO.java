package com.program.user.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @description: 用户DTO
 * @create: 2019-09-24 18:27
 **/
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1093696069494607911L;
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 管理员名称
     */
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-z]\\w{4,24}$",
        message = "非数字开头的由字母、数字、下划线组成的5-25位组合。")
    private String userName;

    /**
     * 管理员昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Size(max = 30, message = "管理员昵称应该小于30个字符")
    private String nickName;

    /**
     * 性别 0：男 1：女
     */
    private Integer gender;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)"
        + "[a-zA-Z0-9\\W]{8,18}$",
        message = "密码必须是包含大写字母、小写字母、数字、特殊符号（不是字母，数字，下划线的字符）的8-18位的组合")
    private String password;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 所属主管ID
     */
    private Long directorAdminId;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者ID
     */
    private Long operator;
}
