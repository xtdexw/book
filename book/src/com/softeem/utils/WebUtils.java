package com.softeem.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.util.Map;

public class WebUtils {

    public static void deleteFile(String path){
        File file = new File("d:" + path);
        file.delete();
    }

    /**
     * 将字符串转换成为int 类型的数据
     *
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            System.out.println("parseInt方法正在使用!");
//            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 把Map 中的值注入到对应的JavaBean 属性中。
     *
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            System.out.println("注入之前：" + bean);
/**
 * 把所有请求的参数都注入到user 对象中
 */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
