/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.spring.ioc.dependency.inject;

import com.example.spring.ioc.dependency.inject.annotation.UserGroup;
import com.example.spring.ioc.dependency.inject.config.UserConfig;
import com.example.spring.ioc.dependency.inject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * {@link Qualifier} 注解依赖注入
 */
@Component
public class QualifierExample {

    /**
     * 1Bean = user2 -> primary =true
     */
    @Autowired
    private User user;

    /**
     * 1 Bean = user1
     */
    @Autowired
    @Qualifier("user")
    private User namedUser;

    /**
     * 6 Beans = user1 + user2 + user3 + user4 + user5 + user6
     */
    @Autowired
    private Collection<User> allUsers;

    /**
     * 4 Beans = user1 + user2 + user3 + user4
     */
    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    /**
     * 2 Beans = user5 + user6
     */
    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(QualifierExample.class);
        applicationContext.register(UserConfig.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找
        QualifierExample example = applicationContext.getBean(QualifierExample.class);

        // 期待输出 superUser Bean
        System.out.println("example.user = " + example.user);
        // 期待输出 user Bean
        System.out.println("example.namedUser = " + example.namedUser);
        // 期待输出 superUser user user1 user2
        System.out.println("example.allUsers = " + example.allUsers);
        // 期待输出 user1 user2
        System.out.println("example.qualifiedUsers = " + example.qualifiedUsers);
        // 期待输出 user3 user4
        System.out.println("example.groupedUsers = " + example.groupedUsers);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
