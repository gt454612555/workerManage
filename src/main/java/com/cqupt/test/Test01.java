package com.cqupt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 获取spring容器中的bean
 */
public class Test01 {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
