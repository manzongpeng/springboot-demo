package com.program.email.common;

import com.program.email.enums.ErrorCodeEnum;
import java.io.Serializable;
import lombok.Data;

/**
 * ResponseBody
 */
@Data
public class ResponseBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String reqId;

    private Object data;

    private ErrorResponse errorResponse;

    public ResponseBody() {

    }

    public ResponseBody(String reqId, Object data) {
        this.reqId = reqId;
        this.data = data;
        this.errorResponse = new ErrorResponse(ErrorCodeEnum.SERVER_SUCCESS.getCode(),
            ErrorCodeEnum.SERVER_SUCCESS.getMessage());
    }

    public ResponseBody(String reqId, Integer code, String msg) {
        this.reqId = reqId;
        this.data = null;
        this.errorResponse = new ErrorResponse(code, msg);
    }
}
