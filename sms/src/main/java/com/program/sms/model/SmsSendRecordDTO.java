package com.program.sms.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SmsSendRecordDTO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 发送类型 1：登录 2：注册 3：忘记密码
     */
    private Integer sendType;

    /**
     * 发送模板数据
     */
    private String sendData;

    /**
     * 发送回执ID
     */
    private String bizId;

    /**
     * 请求状态码，返回OK代表请求成功
     */
    private String code;

    /**
     * 状态码的描述
     */
    private String codeMessage;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除 0：未删除， 1：已删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 数据版本
     */
    private Long rowVersion;

    /**
     * tb_sms_send_record
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @mbg.generated 2019-09-23
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", sendType=").append(sendType);
        sb.append(", sendData=").append(sendData);
        sb.append(", bizId=").append(bizId);
        sb.append(", code=").append(code);
        sb.append(", codeMessage=").append(codeMessage);
        sb.append(", requestId=").append(requestId);
        sb.append(", operator=").append(operator);
        sb.append(", description=").append(description);
        sb.append(", isDel=").append(isDel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", rowVersion=").append(rowVersion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}