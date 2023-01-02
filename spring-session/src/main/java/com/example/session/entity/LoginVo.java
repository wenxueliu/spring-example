package com.example.session.entity;

import java.util.Objects;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
public class LoginVo {
    private String userName;

    private String userPassword;

    public LoginVo() {
    }

    public LoginVo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginVo)) {
            return false;
        }
        LoginVo loginVo = (LoginVo) o;
        return getUserName().equals(loginVo.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }
}
