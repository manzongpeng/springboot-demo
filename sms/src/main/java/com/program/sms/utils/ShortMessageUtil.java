package com.program.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 阿里云短信服务工具类
 **/
@Component
public class ShortMessageUtil {

    private static Logger logger = LoggerFactory.getLogger(ShortMessageUtil.class);

    /**
     * 发送短信
     *
     * @param phoneNumbers 手机号
     * @param signName 短信签名名称
     * @param templateCode 短信模板ID
     * @param templateParam 短信模板变量对应的实际值，JSON格式
     * @return SendSmsResponse
     */
    public SendSmsResponse sendSms(String phoneNumbers, String signName, String templateCode,
        String templateParam) throws ClientException {
        SendSmsResponse sendSmsResponse = new SendSmsResponse();
        // 设置超时时间-可自行调整
        System.setProperty(ShortMessageProperties.defaultConnectTimeout,
            ShortMessageProperties.timeout);
        System.setProperty(ShortMessageProperties.defaultReadTimeout,
            ShortMessageProperties.timeout);
        // 初始化ascClient需要的几个参数
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile
            .getProfile(ShortMessageProperties.regionId, ShortMessageProperties.accessKeyId,
                ShortMessageProperties.accessKeySecret);
        try {
            DefaultProfile
                .addEndpoint(ShortMessageProperties.endPointName, ShortMessageProperties.regionId,
                    ShortMessageProperties.product, ShortMessageProperties.domain);
        } catch (ClientException e1) {
            e1.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNumbers);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${code},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //request.setTemplateParam("{ \"code\":\""+code+"\"}");
        request.setTemplateParam(templateParam);
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(shortMessageProperties.getOutId());
        request.setOutId(ShortMessageProperties.outId);
        // 请求失败这里会抛ClientException异常
        sendSmsResponse = acsClient.getAcsResponse(request);
        logger.info("[sendSms]====sendSmsResponse:{}", sendSmsResponse);
        //返回请求结果
        return sendSmsResponse;
    }

    /**
     * 批量发送短信
     *
     * @param phoneNumberJson 接收短信的手机号码，JSON数组格式
     * @param signNameJson 短信签名名称，JSON数组格式
     * @param templateCode 短信模板CODE
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式
     * @return CommonResponse
     */
    public String sendBatchSms(String phoneNumberJson, String signNameJson, String templateCode,
        String templateParamJson) throws ClientException {
        // 设置超时时间-可自行调整
        System.setProperty(ShortMessageProperties.defaultConnectTimeout,
            ShortMessageProperties.timeout);
        System.setProperty(ShortMessageProperties.defaultReadTimeout,
            ShortMessageProperties.timeout);
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile
            .getProfile(ShortMessageProperties.regionId, ShortMessageProperties.accessKeyId,
                ShortMessageProperties.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(ShortMessageProperties.domain);
        request.setVersion(ShortMessageProperties.version);
        request.setAction(ShortMessageProperties.sendBatchSmsAction);
        request.putQueryParameter("RegionId", ShortMessageProperties.regionId);
        request.putQueryParameter("PhoneNumberJson", phoneNumberJson);
        request.putQueryParameter("SignNameJson", signNameJson);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParamJson", templateParamJson);
        CommonResponse response = new CommonResponse();
        response = client.getCommonResponse(request);
        logger.info("[sendBatchSms]====commonResponse:{}", response);
        System.out.println(response.getData());

        return response.getData();
    }

    /**
     * 查看短信发送记录和发送状态
     *
     * @param phoneNumber 接收短信的手机号码
     * @param sendDate 短信发送日期，支持查询最近30天的记录。格式为yyyyMMdd，例如20181225
     * @param currentPage 分页查看发送记录，指定发送记录的的当前页码
     * @param pageSize 分页查看发送记录，指定每页显示的短信记录数量
     * @return CommonResponse
     */
    public String querySendDetails(String phoneNumber, String sendDate, Long currentPage,
        Long pageSize) {
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile
            .getProfile(ShortMessageProperties.regionId, ShortMessageProperties.accessKeyId,
                ShortMessageProperties.accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(ShortMessageProperties.domain);
        request.setVersion(ShortMessageProperties.version);
        request.setAction(ShortMessageProperties.querySendDetailsAction);
        request.putQueryParameter("RegionId", ShortMessageProperties.regionId);
        request.putQueryParameter("PhoneNumber", phoneNumber);
        request.putQueryParameter("SendDate", sendDate);
        request.putQueryParameter("PageSize", "1");
        request.putQueryParameter("CurrentPage", "1");
        request.putQueryParameter("BizId", "12");
        CommonResponse response = new CommonResponse();
        try {
            response = client.getCommonResponse(request);
            logger.info("[querySendDetails]====commonResponse:{}", response);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return response.getData();
    }

}