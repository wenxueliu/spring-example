package com.example.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.Ordered;

/**
 * @author liuwenxue
 * @date 2022-10-30
 */
public class EventUtils {
    private final static Logger logger = LoggerFactory.getLogger(EventUtils.class);

    public static void print(ApplicationEvent event, String methodName, int order) {
        logger.info("[methodName={}] [order={}] : {}\n",
                methodName, order, event.getClass().getName());
    }

    public static void print(ApplicationEvent event, String methodName) {
        logger.info("[methodName={}] [order={}] : {}\n",
                methodName, Ordered.LOWEST_PRECEDENCE, event.getClass().getName());
    }

    public static void print(ApplicationContextEvent event, String methodName) {
        System.out.printf("[appId=%s] [methodName=%s] [order=%d] : %s\n",
                event.getApplicationContext().getId(),
                methodName, Ordered.LOWEST_PRECEDENCE, event.getClass().getName());
    }
}
