package com.mate.im.base.utils;

import com.google.common.base.CaseFormat;

/**
 * Bean 名称转换工具类
 *
 * @author yuanxmo
 */
public class BeanNameUtils {

    /**
     * 把一个策略名称转换成beanName
     * <pre>
     *     如 USER, LoginService -> userLoginService
     * </pre>
     *
     * @param strategyName
     * @param serviceName
     * @return
     */
    public static String getBeanName(String strategyName, String serviceName) {
        return CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL).convert(strategyName) + serviceName;
    }
}
