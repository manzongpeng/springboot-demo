package com.program.sms.utils;

import lombok.Data;

@Data
public class SystemProperty {

	/**
	 * request ID
	 */
	private String reqId;

	/**
	 * Token
	 */
	private String token;

	/**
	 * Login user id
	 */
	private Long userId;

	/**
	 * Login user name
	 */
	private String userName;

	/**
	 * vin
	 */
	private String vin;

	/**
	 * type
	 */
	private Integer type;

	public SystemProperty() {
	}

	public SystemProperty(String reqId, Long userId, String userName) {
		this.reqId = reqId;
		this.userId = userId;
		this.userName = userName;
	}

}
