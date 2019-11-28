package com.program.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @description: 查询管理员结果DTO
 * @create: 2019-10-11 18:13
 **/
@Data
public class UserQueryResDTO implements Serializable {

    private static final long serialVersionUID = -1507432292084926439L;
    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 管理员昵称
     */
    private String nickName;

    /**
     * 性别 0：男 1：女
     */
    private Integer gender;

    /**
     * 角色名
     */
    private List<String> roleName;

    /**
     * 角色名
     */
    private List<String> roleIdentify;

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
     * 状态 0：正常 1：禁用， 默认 0
     */
    private Integer state;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
