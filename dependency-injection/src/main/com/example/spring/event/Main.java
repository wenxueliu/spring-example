package com.example.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
* @author liuwenxue
* @date 2022-10-30
*/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EventConfiguration.class);

        context.refresh();

        AwareBusiness awareBusiness = context.getBean(AwareBusiness.class);
        awareBusiness.handler();

        AutowiredBusiness autowiredBusiness = context.getBean(AutowiredBusiness.class);
        autowiredBusiness.handler();

        Thread.sleep(2000);

        context.close();
    }
}