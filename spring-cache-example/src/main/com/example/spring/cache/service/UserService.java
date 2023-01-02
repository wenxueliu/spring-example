package com.example.spring.cache.service;

import com.example.spring.cache.domain.UserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {
    @Cacheable(key = "#username")
    UserDto getUser(String username);

    @Cacheable(key = "#username")
    UserDto getUserBy(String username);

    @CachePut(key = "#username")
    List<UserDto> save(String username);

    @CacheEvict(key = "#username")
    List<UserDto> deleteUser(String username);

}
