package com.example.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class HierarchicalEventExample {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyApplicationContextListener.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setId("current-context");
        context.setParent(parentContext);
        context.register(MyApplicationContextListener.class);


        parentContext.refresh();
        context.refresh();

        parentContext.publishEvent(new MyApplicationContextEvent(context));
        context.publishEvent(new MyApplicationContextEvent(context));


        context.close();
        parentContext.close();
    }
}
