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

import com.example.spring.ioc.dependency.inject.model.ExampleA;
import com.example.spring.ioc.dependency.inject.model.ExampleB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanInitExample {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(ExampleB.class);
        applicationContext.register(ExampleA.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找
        ExampleB exampleB = applicationContext.getBean(ExampleB.class);
        ExampleA exampleA = applicationContext.getBean(ExampleA.class);

        //System.out.println("example.user = " + example.user);
        //System.out.println("example.user = " + example.person);
        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
