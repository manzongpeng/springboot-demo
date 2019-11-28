package com.program.user.config;

import com.program.user.common.ApiException;
import com.program.user.common.ResponseBody;
import com.program.user.controller.base.BaseController;
import com.program.user.enums.ErrorCodeEnum;
import com.program.user.utils.ResultUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class ExceptionConfiguration extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionConfiguration.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseBody errorHandle(Exception e) {

        /**
         * controller 层参数校验
         */
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            logger.error("{} ===> {} ===> msg: {}", "ExceptionHandle", "errorHandle",
                bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.getErrorResponse(getCurrentReqId(),
                ErrorCodeEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        if (e instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e).getMessage();
            return ResultUtil.getErrorResponse(getCurrentReqId(),
                ErrorCodeEnum.PARAM_ERROR.getCode(), message.split(":")[1]);
        }
        /**
         * service 自定义返回异常<br/>
         * TODO UndeclaredThrowableException异常为自定义异常的包装，未找到原因，此处强转解决。
         */
        if (e instanceof UndeclaredThrowableException) {
            ApiException apiException;
            try {
                InvocationTargetException undeclaredThrowable =
                    (InvocationTargetException) ((UndeclaredThrowableException) e)
                        .getUndeclaredThrowable();
                undeclaredThrowable.getTargetException();
                //if (undeclaredThrowable.getTargetException() instanceof RpcException) {
                //    logger.error("{} ===> {} ===> error: {}", "ExceptionHandle", "errorHandle",
                //        ErrorCodeEnum.DUBBO_ERROR.getMessage());
                //}
                if (undeclaredThrowable.getTargetException() instanceof ApiException) {
                    apiException = (ApiException) undeclaredThrowable.getTargetException();
                    logger.error("{} ===> {} ===> error: {}", "ExceptionHandle", "errorHandle",
                        apiException.toString());
                    return ResultUtil.getErrorResponse(getCurrentReqId(), apiException.getCode(),
                        apiException.getMsg());
                }
            } catch (Exception e1) {
                logger.error("{} ===> {} ===> {}", "ExceptionHandle", "errorHandle", "异常转换错误", e1);
                return ResultUtil.getErrorResponse(getCurrentReqId(),
                    ErrorCodeEnum.SERVER_ERROR.getCode(),
                    ErrorCodeEnum.SERVER_ERROR.getMessage());
            }
        }
        if (e instanceof ApiException) {
            logger.error("{} ===> {} ===> error: {}", "ExceptionHandle", "errorHandle",
                ((ApiException) e).getMsg());
            return ResultUtil.getErrorResponse(getCurrentReqId(), ((ApiException) e).getCode(),
                ((ApiException) e).getMsg());
        }
        /**
         * 其他异常返回500
         */
        logger.error("{} ===> {} ===>", "ExceptionHandle", "errorHandle", e);
        return ResultUtil.getErrorResponse(getCurrentReqId(), ErrorCodeEnum.SERVER_ERROR.getCode(),
            ErrorCodeEnum.SERVER_ERROR.getMessage());
    }

}
