package com.example.cache.service;


import com.example.cache.entity.User;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
public interface UserService {
    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(long id);
}
