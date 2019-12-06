package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xxx
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 性别 0：未设定 1：男  2：女，默认0
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

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
     * 状态 0：正常 1：禁用， 默认 0
     */
    private Integer state;

    /**
     * 状态 0：正常 ，1：已删除 默认 0
     */
    private Integer isDel;

    /**
     * 描述
     */
    private String description;

    /**
     * 操作者ID
     */
    private Long operator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
