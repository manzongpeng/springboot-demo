package com.program.user.utils;


/**
 * @description: 手机验证部分配置
 **/
public class ShortMessageProperties {

    // 设置超时时间-可自行调整
    public static final String defaultConnectTimeout = GetSmsPropertiesUtil.getValue("defaultConnectTimeout");
    public static final String defaultReadTimeout = GetSmsPropertiesUtil.getValue("defaultReadTimeout");
    public static final String timeout = GetSmsPropertiesUtil.getValue("timeout");
    // TODO 替换成AK (产品密钥)
    public static final String accessKeyId = GetSmsPropertiesUtil.getValue("accessKeyId");
    public static final String accessKeySecret = GetSmsPropertiesUtil.getValue("accessKeySecret");
    // 阿里云配置自己的短信签名填入
    //登录 1
    public static final String loginSignName = GetSmsPropertiesUtil.getValue("loginSignName");
    public static final String loginTemplateCode = GetSmsPropertiesUtil.getValue("loginTemplateCode");
    //注册 2
    public static final String registerSignName = GetSmsPropertiesUtil.getValue(
        "registerSignName");
    public static final String registerTemplateCode = GetSmsPropertiesUtil.getValue("registerTemplateCode");
    //忘记密码 3
    public static final String forgetPwdSignName = GetSmsPropertiesUtil.getValue("forgetPwdSignName");
    public static final String forgetPwdTemplateCode = GetSmsPropertiesUtil.getValue("forgetPwdTemplateCode");
    // 短信API产品名称（短信产品名固定，无需修改）
    public static final String product = GetSmsPropertiesUtil.getValue("product");
    // 短信API产品域名（接口地址固定，无需修改）
    public static final String domain = GetSmsPropertiesUtil.getValue("domain");
    // 初始化ascClient需要的几个参数
    public static final String endPointName = GetSmsPropertiesUtil.getValue("endPointName");
    public static final String regionId = GetSmsPropertiesUtil.getValue("regionId");
    //TODO outId设置
    public static final String outId = GetSmsPropertiesUtil.getValue("outId");
    //批量发送版本
    public static final String version = GetSmsPropertiesUtil.getValue("version");
    //系统规定参数
    public static final String sendBatchSmsAction = GetSmsPropertiesUtil.getValue("sendBatchSmsAction");
    public static final String querySendDetailsAction = GetSmsPropertiesUtil.getValue("querySendDetailsAction");

}