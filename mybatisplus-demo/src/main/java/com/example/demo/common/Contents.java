package com.example.demo.common;

/**
 * Final String Contents
 */
public class Contents {

    public static final String SYMBOL_SPOT = ".";

    public static final String SYMBOL_BAR = "-";

    public static final String SYMBOL_PERCENT = "%";

    public static final String SYMBOL_N = "\n";

    public static final String SYMBOL_QUESTION = "?";

    public static final String STR_UTF_8 = "UTF-8";

    public static final int SNOW_WORKERID = 1;

    public static final int SNOW_DATACENTERID = 5;

    public static final String STR_UNKNOW = "unknown";

    /**
     * token请求头
     */
    public static final String TOKEN_HEADER = "token";
    /**
     * token过期时间
     */
    public static final Long EXPIRE_TIME = System.currentTimeMillis() + 12 * 60 * 60 * 1000;

    /**
     * 盐长度
     */
    public static final int SALT_RANDOM_ALPHANUMERIC = 20;

}
