package com.flowable.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @describe: 获取springcontext中的bean
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.context = context;
    }

    /**
     * 获取容器中的实例
     *
     * @param beanId 注入在Spring容器中的bean的ID 默认为类名首字母小写
     * @param clazz  获取的bean的实际的类的class
     */
    public static <T> T getBean(String beanId, Class<T> clazz) {
        return context.getBean(beanId, clazz);
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
