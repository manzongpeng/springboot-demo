package com.program.user.utils;

import cn.hutool.core.util.StrUtil;
import com.program.user.common.ErrorResponse;
import com.program.user.common.ResponseBody;
import com.program.user.enums.ErrorCodeEnum;


public class ResultUtil {

    private volatile ResponseBody responseBody = null;

    public static ResponseBody getSuccessResponse(String reqId) {
        return new ResultUtil()
            .getResponseBody(reqId, null, Integer.toString(ErrorCodeEnum.SERVER_SUCCESS.getCode()),
                ErrorCodeEnum.SERVER_SUCCESS.getMessage());
    }

    public static ResponseBody getSuccessResponse(String reqId, Object reqData) {
        return new ResultUtil().getResponseBody(reqId, reqData,
            Integer.toString(ErrorCodeEnum.SERVER_SUCCESS.getCode()),
            ErrorCodeEnum.SERVER_SUCCESS.getMessage());
    }

    public static ResponseBody getErrorResponse(String reqId, Integer code, String msg) {
        return new ResultUtil().getResponseBody(reqId, null, Integer.toString(code), msg);
    }

    public ResponseBody getResponseBody(String reqId, Object reqData, String errorCode,
        String errorMsg) {
        if (responseBody == null) {
            synchronized (this) {
                if (responseBody == null) {
                    responseBody = new ResponseBody();
                }
            }
        }
        responseBody.setReqId(reqId);
        responseBody.setData(reqData);
        ErrorResponse errorResponse = null;
        if (StrUtil.isNotBlank(errorCode) && StrUtil.isNotBlank(errorMsg)) {
            errorResponse = new ErrorResponse();
            errorResponse.setCode(errorCode);
            errorResponse.setMsg(errorMsg);
        }
        responseBody.setErrorResponse(errorResponse);
        return responseBody;
    }

}
