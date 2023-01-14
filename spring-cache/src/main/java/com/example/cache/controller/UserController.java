package com.example.cache.controller;


import com.example.cache.entity.User;
import com.example.cache.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuwenxue
 * @date 2023-01-02
 */
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    @PostMapping("save")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 删除
     *
     * @param user key值
     */
    @PutMapping("put")
    void update(@RequestBody User user) {
        userService.update(user);
    }

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    @GetMapping("get")
    public User get(long id) {
        return userService.get(id);
    }

    /**
     * 删除
     *
     * @param id key值
     */
    @DeleteMapping("delete")
    void delete(long id) {
        userService.delete(id);
    }
}
