package com.example.spring.ioc.dependency.inject.config;


import com.example.spring.ioc.dependency.inject.annotation.UserGroup;
import com.example.spring.ioc.dependency.inject.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
* @author liuwenxue
* @date 2022-10-15
*/
@Configuration
public class UserConfig {
    public static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Bean(name = "user")
    public User user1() {
        return createUser(1L);
    }

    @Bean
    @Primary
    public User user2() {
        User user = new User();
        user.setId(2L);
        return user;
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public User user3() {
        return createUser(3L);
    }


    @Bean
    @Qualifier // 进行逻辑分组
    public static User user4() {
        return createUser(4L);
    }

    @Bean
    @UserGroup
    public static User user5() {
        return createUser(5L);
    }

    @Bean
    @UserGroup
    public static User user6() {
        return createUser(6L);
    }
}
