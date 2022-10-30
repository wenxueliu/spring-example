package com.example.spring.ioc.dependency.inject.model;

/**
 * @author liuwenxue
 * @date 2022-10-15
 */
public class User {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
