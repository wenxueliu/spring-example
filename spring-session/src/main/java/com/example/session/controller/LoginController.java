package com.example.session.controller;

import com.example.session.entity.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
@RestController
public class LoginController {
    private static final String CURRENT_USER = "currentUser";

    Map<String, String> userInfo = new HashMap<>();

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param request 请求
     */
    @GetMapping("/login")
    public String login(String userName, String password, HttpServletRequest request) {
        UserVo userVo = new UserVo(userName, password);
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_USER, userVo);
        System.out.println("create session, sessionId is:" + session.getId());
        return "ok";
    }

    @GetMapping("/query")
    public String querySessionId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "please /login first";
        }
        System.out.println("current's user is:" + session.getId() + "in session");
        return session.getId();
    }

    /**
     * 登出
     *
     * @param request 请求
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        return "ok";
    }
}
