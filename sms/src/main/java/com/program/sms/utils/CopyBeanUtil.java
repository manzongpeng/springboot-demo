package com.program.sms.utils;

import cn.hutool.core.bean.BeanUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * copy bean util
 *
 */
public class CopyBeanUtil {

    public static <T> List<T> convertList(List<?> fromValues, Class<T> toValueType) {
        List<T> instances = new ArrayList<T>();
        if (fromValues == null || fromValues.isEmpty()) {
            return instances;
        }
        fromValues.stream().forEach(fromValue -> {
            T instance = null;
            try {
                if (fromValue != null) {
                    instance = toValueType.newInstance();
                    BeanUtil.copyProperties(fromValue, instance);
                    instances.add(instance);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return instances;
    }

    public static <T> T convert(Object fromValue, Class<T> toValueType) {
        T instance = null;
        try {
            instance = toValueType.newInstance();
            if (fromValue != null) {
                BeanUtil.copyProperties(fromValue, instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
