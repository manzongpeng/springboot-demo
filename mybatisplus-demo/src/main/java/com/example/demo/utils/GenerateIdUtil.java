package com.example.demo.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.example.demo.common.Contents;

/**
 * @Author: xxx
 * @License: (C) Copyright 2019-2099, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2019/12/4 19:46
 * @Version: 1.0
 * @Description: 生成ID工具类
 */
public class GenerateIdUtil {

    public static Long nextId() {
        return IdUtil.createSnowflake(Contents.SNOW_WORKERID, Contents.SNOW_DATACENTERID).nextId();
    }

    public static String nextUUID() {
        return UUID.fastUUID().toString().replace(Contents.SYMBOL_BAR, "");
    }
}
