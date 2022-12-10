package com.example.spring.cache.service.impl;

import com.example.spring.cache.dao.UserRepository;
import com.example.spring.cache.domain.UserDto;
import com.example.spring.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;


@CacheConfig(cacheNames = "users") // 指定缓存名称，在本类中是全局的
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 缓存 key 是 username 的数据到缓存 users 中，
     * 如果没有指定 key，则方法参数作为 key 保存到缓存中
     * <p>
     * 二选一
     */
    //@Cacheable(key = "#username", keyGenerator = "userKeyGenerator")
    @Override
    public UserDto getUser(String username) {
        System.out.println("从数据库中获取数据，而不是读取缓存");
        return userRepository.getUser(username);
    }

    /**
     * 缓存 key 是 username 的数据到缓存 users 中，
     * 如果没有指定 key，则方法参数作为 key 保存到缓存中
     * <p>
     * 二选一
     */
    //@Cacheable(key = "#username", keyGenerator = "userKeyGenerator")
    @Override
    public UserDto getUserBy(String username) {
        System.out.println("从数据库中获取数据，而不是读取缓存");
        return userRepository.getUser(username);
    }

    /**
     * 新增或更新缓存中的数据
     */
    @Override
    public List<UserDto> save(String username) {
        System.out.println("保存数据");
        return userRepository.save(username);
    }

    /**
     * 从缓存 users 中删除 key 是 username 的数据
     */
    @Override
    public List<UserDto> deleteUser(String username) {
        System.out.println("从数据库中删除数据，以及缓存中的数据");
        return userRepository.deleteUser(username);
    }
}
