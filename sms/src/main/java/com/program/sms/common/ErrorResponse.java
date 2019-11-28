package com.program.sms.common;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName ErrorResponse
 * @Description 异常信息描述
 * @Version v1.0
 **/
@Data
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 8945423401955643722L;

    private String code;

    private String msg;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String msg) {
        this.code = Integer.toString(code);
        this.msg = msg;
    }

}
