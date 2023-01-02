package com.example.cache.entity;

import java.util.Objects;

/**
 * @author liuwenxue
 * @date 2023-01-01
 */
public class User {
    /**
     * 主键id
     */
    private long id;
    /**
     * 姓名
     */
    private String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(id, user.id);
    }
}
