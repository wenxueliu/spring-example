package com.example.spring.ioc.dependency.inject.model;

/**
 * @author liuwenxue
 * @date 2022-10-23
 */
public class ExampleX {
    private ExampleY exampleY;

    public ExampleX(ExampleY exampleY) {
        this.exampleY = exampleY;
    }

    public ExampleY getExampleY() {
        return exampleY;
    }

    public void setExampleY(ExampleY exampleY) {
        this.exampleY = exampleY;
    }
}
