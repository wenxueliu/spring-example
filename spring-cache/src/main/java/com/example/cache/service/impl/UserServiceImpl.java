package com.example.cache.service.impl;


import com.example.cache.entity.User;
import com.example.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user", cacheManager = "userService")
public class UserServiceImpl implements UserService {
    /**
     * 模拟数据库
     */
    private static final Map<Long, User> DATABASES = new ConcurrentHashMap();

    /**
     * 初始化数据
     */
    static {
        DATABASES.put(1L, new User(1L, "user1"));
        DATABASES.put(2L, new User(2L, "user2"));
        DATABASES.put(3L, new User(3L, "user3"));
    }

    /**
     * 保存用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    @CachePut(key = "#user.id")
    @Override
    public User save(User user) {
        DATABASES.put(user.getId(), user);
        log.info("保存用户【user】= {}", user);
        return user;
    }

    /**
     * 修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    @Caching(evict = @CacheEvict(key = "#user.id"),
            put = @CachePut(key = "#user.id"))
    @Override
    public User update(User user) {
        DATABASES.put(user.getId(), user);
        log.info("保存用户【user】= {}", user);
        return user;
    }

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    @Cacheable(key = "#id", condition = "#id > 1")
    @Override
    public User get(long id) {
        // 我们假设从数据库读取
        log.info("查询用户【id】= {}", id);
        return DATABASES.get(id);
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @CacheEvict(key = "#id")
    @Override
    public void delete(long id) {
        DATABASES.remove(id);
        log.info("删除用户【id】= {}", id);
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @Caching()
    public void deleteAll(long id) {
        DATABASES.remove(id);
        log.info("删除用户【id】= {}", id);
    }
}
