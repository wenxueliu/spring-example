package com.example.spring.cache.service.impl;

import com.example.spring.cache.domain.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JackRen
 * @date 2021-03-05 16:41
 * @description:
 */
public class DataFactory {

    private DataFactory() {
    }

    private static List<UserDto> userDtoList;

    static {
        userDtoList = new ArrayList<>();
        UserDto user = null;
        for (int i = 0; i < 10; i++) {
            user = new UserDto();
            user.setName("jack" + i);
            user.setAge("2" + i);
            userDtoList.add(user);
        }
    }

    public static List<UserDto> getUserDaoList() {
        return userDtoList;
    }

}
