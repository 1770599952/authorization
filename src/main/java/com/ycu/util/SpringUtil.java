package com.ycu.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring工具类
 */
public class SpringUtil implements ApplicationContextAware {

    /** 上下文 */
	@Autowired
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 根据Bean ID获取Bean
     * 
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        if(applicationContext == null){
            return null;
        }
        return applicationContext.getBean(beanId);
    }
}