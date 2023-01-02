package com.example.spring.cache.controller;

import com.example.spring.cache.domain.UserDto;
import com.example.spring.cache.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CacheResourceController {
    private final UserService userService;

    public CacheResourceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        // 获取数据
        UserDto user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<List<UserDto>> save(@PathVariable String username) {
        List<UserDto> userDtoList = userService.save(username);

        return ResponseEntity.ok(userDtoList);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<List<UserDto>> delete(@PathVariable String username) {
        List<UserDto> userDtoList = userService.deleteUser(username);

        return ResponseEntity.ok(userDtoList);
    }
}