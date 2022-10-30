package com.example.spring.ioc.dependency.inject.model;

/**
 * @author liuwenxue
 * @date 2022-10-23
 */
public class ExampleY {
    private ExampleX exampleX;

    public ExampleY(ExampleX exampleX) {
        this.exampleX = exampleX;
    }

    public ExampleX getExampleX() {
        return exampleX;
    }

    public void setExampleX(ExampleX exampleX) {
        this.exampleX = exampleX;
    }
}
