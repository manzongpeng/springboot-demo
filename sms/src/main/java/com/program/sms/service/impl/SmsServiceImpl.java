package com.program.sms.service.impl;


import static com.program.sms.utils.ShortMessageProperties.loginSignName;
import static com.program.sms.utils.ShortMessageProperties.loginTemplateCode;
import static com.program.sms.utils.ShortMessageProperties.registerSignName;
import static com.program.sms.utils.ShortMessageProperties.registerTemplateCode;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.program.sms.common.ApiException;
import com.program.sms.common.Contents;
import com.program.sms.common.PageModel;
import com.program.sms.common.PageRequest;
import com.program.sms.entity.SmsSendRecord;
import com.program.sms.entity.SmsSendRecordExample;
import com.program.sms.enums.SendTypeEnum;
import com.program.sms.enums.SmsErrorCodeEnum;
import com.program.sms.enums.SmsStatusEnum;
import com.program.sms.mapper.SmsSendRecordMapper;
import com.program.sms.model.SmsQueryParamDTO;
import com.program.sms.model.SmsSendParamDTO;
import com.program.sms.model.SmsSendRecordDTO;
import com.program.sms.service.SmsService;
import com.program.sms.template.SmsLoginTemplateParam;
import com.program.sms.template.SmsRegisterTemplateParam;
import com.program.sms.utils.CopyBeanUtil;
import com.program.sms.utils.GenerateIdUtil;
import com.program.sms.utils.ShortMessageUtil;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 发送短信实现
 **/
@Service
public class SmsServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private ShortMessageUtil shortMessageUtil;
    @Autowired
    private SmsSendRecordMapper smsSendRecordMapper;

    @Override
    public void sendSms(SmsSendParamDTO smsSendParamDTO) throws ApiException {
        if (smsSendParamDTO.getSendType() < 1 || smsSendParamDTO.getSendType() > 5) {
            throw new ApiException(SmsErrorCodeEnum.SEND_TYPE_ERROR.getCode(),
                SmsErrorCodeEnum.SEND_TYPE_ERROR.getMessage());
        }
        if (smsSendParamDTO.getSendType().equals(SendTypeEnum.SIGN_IN.getType())) {
            sendLoginSms(smsSendParamDTO);
        }
        if (smsSendParamDTO.getSendType().equals(SendTypeEnum.REGISTER.getType())) {
            sendRegisterSms(smsSendParamDTO);
        }
    }

    /**
     * 发送登录短信
     */
    public void sendLoginSms(SmsSendParamDTO smsSendParamDTO) throws ApiException {
        SmsLoginTemplateParam smsLoginTemplateParam = (SmsLoginTemplateParam) smsSendParamDTO
            .getTemplateParam();
        String[] splitPhoneNumber = smsSendParamDTO.getPhoneNumbers().split(",");
        if (ArrayUtil.isNotEmpty(splitPhoneNumber)) {
            //循环发送并且插入每条记录
            SendSmsResponse sendSmsResponse;
            for (String phoneNumber : splitPhoneNumber) {
                try {
                    sendSmsResponse = shortMessageUtil.sendSms(phoneNumber, loginSignName,
                        loginTemplateCode, JSONUtil.toJsonStr(smsLoginTemplateParam));
                    //发送结果入库
                    this.insertSmsSendRecord(phoneNumber, SendTypeEnum.SIGN_IN.getType(),
                        JSONUtil.toJsonStr(smsLoginTemplateParam),
                        sendSmsResponse, smsSendParamDTO.getOperator(),
                        smsSendParamDTO.getDescription());
                } catch (ClientException e) {
                    logger.error("{} ===> {} ===>  {}", "SmsServiceImpl", "sendLoginSms",
                        e.getErrCode());
                    throw new ApiException(SmsErrorCodeEnum.SEND_SMS_FAIL.getCode(),
                        e.getMessage());
                }
            }
        }
    }

    /**
     * 发送注册短信
     */
    public void sendRegisterSms(SmsSendParamDTO smsSendParamDTO) throws ApiException {
        SmsRegisterTemplateParam smsRegisterTemplateParam =
            (SmsRegisterTemplateParam) smsSendParamDTO.getTemplateParam();
        String[] splitPhoneNumber = smsSendParamDTO.getPhoneNumbers().split(",");
        if (ArrayUtil.isNotEmpty(splitPhoneNumber)) {
            SendSmsResponse sendSmsResponse;
            //循环发送并且插入每条记录
            for (String phoneNumber : splitPhoneNumber) {
                try {
                    sendSmsResponse = shortMessageUtil.sendSms(phoneNumber, registerSignName,
                        registerTemplateCode, JSONUtil.toJsonStr(smsRegisterTemplateParam));
                    //发送结果入库
                    this.insertSmsSendRecord(phoneNumber, SendTypeEnum.REGISTER.getType(),
                        JSONUtil.toJsonStr(smsRegisterTemplateParam),
                        sendSmsResponse, smsSendParamDTO.getOperator(),
                        smsSendParamDTO.getDescription());
                } catch (ClientException e) {
                    logger.error("{} ===> {} ===>  {}", "SmsServiceImpl", "sendRegisterSms",
                        e.getErrCode());
                    throw new ApiException(SmsErrorCodeEnum.SEND_SMS_FAIL.getCode(),
                        e.getMessage());
                }
            }
        }
    }

    /**
     * 查询发送短信列表
     */
    @Override
    public PageModel querySendSmsList(PageRequest<SmsQueryParamDTO> pageRequest)
        throws ApiException {
        SmsQueryParamDTO smsQueryParamDTO = pageRequest.getSearch();
        SmsSendRecordExample smsSendRecordExample = new SmsSendRecordExample();
        SmsSendRecordExample.Criteria smsSendRecordExampleCriteria = smsSendRecordExample
            .createCriteria();
        if (smsQueryParamDTO != null) {
            String phoneNumber = smsQueryParamDTO.getPhoneNumber();
            Date startDate = smsQueryParamDTO.getStartDate();
            Date endDate = smsQueryParamDTO.getEndDate();
            if (phoneNumber != null) {
                smsSendRecordExampleCriteria.andPhoneNumberLike(
                    Contents.SYMBOL_PERCENT + phoneNumber + Contents.SYMBOL_PERCENT);
            }
            if (null != startDate && null != endDate) {
                smsSendRecordExampleCriteria.andCreateTimeBetween(startDate, endDate);
            }
        }
        Integer pageNum = pageRequest.getPageNum();
        Integer pageSize = pageRequest.getPageSize();
        //计算Total
        Long total = smsSendRecordMapper.countByExample(smsSendRecordExample);
        PageHelper.startPage(pageNum, pageSize);
        //未删除数据
        smsSendRecordExampleCriteria.andIsDelEqualTo(SmsStatusEnum.NOT_DELETE.getStatus());
        List<SmsSendRecord> smsSendRecordList = smsSendRecordMapper
            .selectByExample(smsSendRecordExample);
        List<SmsSendRecordDTO> smsSendRecordDTOList = CopyBeanUtil
            .convertList(smsSendRecordList, SmsSendRecordDTO.class);
        PageModel pageModel = new PageModel(pageNum, pageSize);
        pageModel.setTotalNum(Math.toIntExact(total));
        pageModel.setData(smsSendRecordDTOList);
        return pageModel;
    }

    /**
     * 插入短信发送记录
     *
     * @param phoneNumber 手机号
     * @param sendType 发送类型
     * @param templateParam 模板参数
     * @param sendSmsResponse 发送结果响应
     */
    private void insertSmsSendRecord(String phoneNumber, Integer sendType, String templateParam,
        SendSmsResponse sendSmsResponse, Long operator, String description) {
        SmsSendRecord smsSendRecord = new SmsSendRecord();
        smsSendRecord.setId(GenerateIdUtil.nextId());
        smsSendRecord.setPhoneNumber(phoneNumber);
        smsSendRecord.setSendType(sendType);
        smsSendRecord.setSendData(templateParam);
        smsSendRecord.setBizId(sendSmsResponse.getBizId());
        smsSendRecord.setCode(sendSmsResponse.getCode());
        smsSendRecord.setCodeMessage(sendSmsResponse.getMessage());
        smsSendRecord.setRequestId(sendSmsResponse.getRequestId());
        smsSendRecord.setOperator(operator);
        smsSendRecord.setDescription(description);
        smsSendRecordMapper.insertSelective(smsSendRecord);
    }


}
