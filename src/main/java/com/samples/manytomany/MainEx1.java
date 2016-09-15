package com.samples.manytomany;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by edara on 9/14/16.
 */
public class MainEx1 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ServiceEx1 serviceEx1 = (ServiceEx1) context.getBean("serviceEx1");
        serviceEx1.run();
    }
}
