package com.program.sms.service;

import com.program.sms.common.ApiException;
import com.program.sms.common.PageModel;
import com.program.sms.common.PageRequest;
import com.program.sms.model.SmsQueryParamDTO;
import com.program.sms.model.SmsSendParamDTO;
import com.program.sms.model.SmsSendRecordDTO;

/**
 * @description: 短信发送service
 **/
public interface SmsService {

    /**
     * 发送短信
     */
    void sendSms(SmsSendParamDTO smsSendParamDTO) throws ApiException;

    /**
     * 查询发送记录
     */
    PageModel<SmsSendRecordDTO> querySendSmsList(PageRequest<SmsQueryParamDTO> pageRequest)
        throws ApiException;

}
