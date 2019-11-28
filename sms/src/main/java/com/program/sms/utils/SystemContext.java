package com.program.sms.utils;

public class SystemContext {

	private static final ThreadLocal<SystemProperty> PROPS = new ThreadLocal<SystemProperty>();

	public SystemContext() {
	}

	public static SystemProperty getSystemProperty() {
		return (SystemProperty) PROPS.get();
	}

	public static void setSystemProperty(SystemProperty sysProp) {
		PROPS.set(sysProp);
	}

	public static void cleanSystemProperty() {
		PROPS.remove();
	}

	public static String getReqId() {
		return getSystemProperty().getReqId();
	}

}
