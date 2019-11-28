package com.program.sms.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description: 短信查询参数DTO
 **/
@Data
public class SmsQueryParamDTO implements Serializable {

    private static final long serialVersionUID = -1393197960938665345L;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
}
