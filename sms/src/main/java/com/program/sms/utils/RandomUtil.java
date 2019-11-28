package com.program.sms.utils;

import java.util.Random;

/**
 * @description: 生成随机数工具类
 **/
public class RandomUtil {

    /**
     * 生成随机数
     *
     * @param charCount 随机数位数
     * @return
     */
    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    private static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }


}
