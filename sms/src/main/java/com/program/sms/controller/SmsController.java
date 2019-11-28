package com.program.sms.controller;

import com.program.sms.common.ApiException;
import com.program.sms.common.PageModel;
import com.program.sms.common.PageRequest;
import com.program.sms.common.ResponseBody;
import com.program.sms.model.SmsQueryParamDTO;
import com.program.sms.model.SmsSendParamDTO;
import com.program.sms.model.SmsSendRecordDTO;
import com.program.sms.service.SmsService;
import com.program.sms.template.SmsLoginTemplateParam;
import com.program.sms.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 短信控制层
 **/
@Api("短信控制层")
@RestController
@RequestMapping("/web/sms/v1")
@Validated
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    @ApiOperation("发送短信")
    public ResponseBody sendSms(
        @RequestBody SmsSendParamDTO<SmsLoginTemplateParam> smsSendParamDTO) {
        smsService.sendSms(smsSendParamDTO);
        return ResultUtil.getSuccessResponse(UUID.randomUUID().toString());
    }

    @PostMapping("/list")
    @ApiOperation("查询发送记录")
    public ResponseBody queryMenuList(
        @RequestBody @Validated PageRequest<SmsQueryParamDTO> pageRequest) throws ApiException {
        PageModel<SmsSendRecordDTO> smsSendRecordDTOPageModel = smsService
            .querySendSmsList(pageRequest);
        return ResultUtil
            .getSuccessResponse(UUID.randomUUID().toString(), smsSendRecordDTOPageModel);
    }

}
