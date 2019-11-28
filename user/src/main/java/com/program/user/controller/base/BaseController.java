package com.program.user.controller.base;


import com.program.user.utils.SystemContext;
import com.program.user.utils.SystemProperty;

public class BaseController {

    public String getCurrentReqId() {
        SystemProperty systemProperty = SystemContext.getSystemProperty();
        if (null != systemProperty) {
            return systemProperty.getReqId();
        }
        return null;
    }

    public String getLoginUserName() {
        SystemProperty systemProperty = SystemContext.getSystemProperty();
        if (null != systemProperty) {
            return systemProperty.getUserName();
        }
        return null;
    }

    public Long getLoginUserId() {
        SystemProperty systemProperty = SystemContext.getSystemProperty();
        if (null != systemProperty) {
            return systemProperty.getUserId();
        }
        return null;
    }
}
