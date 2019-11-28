package com.program.email.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.program.email.common.Contents;

/**
 * @description: 生成ID工具类
 **/
public class GenerateIdUtil {

	public static Long nextId() {
		return IdUtil.createSnowflake(Contents.SNOW_WORKERID, Contents.SNOW_DATACENTERID).nextId();
	}

	public static String nextUUID() {
		return UUID.fastUUID().toString().replace(Contents.SYMBOL_BAR, "");
	}
}
