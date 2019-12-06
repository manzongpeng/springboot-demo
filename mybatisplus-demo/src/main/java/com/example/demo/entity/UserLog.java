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
 * 用户登录日志
 * </p>
 *
 * @author xxx
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user_log")
public class UserLog extends Model<UserLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 操作类型 1:登录
     */
    private Integer typeId;

    /**
     * 设备来源 1:PC 2:WX_APP 3:IOS 4:ANDROID 5:WAP
     */
    private Integer sourceId;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 相关ID
     */
    private Long relativeId;

    /**
     * 是否删除 0：未删除，1： 已删除
     */
    private Integer isDel;

    /**
     * 操作者
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
     * 数据版本
     */
    private Long rowVersion;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
