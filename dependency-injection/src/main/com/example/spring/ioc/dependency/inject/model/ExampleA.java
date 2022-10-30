package com.example.spring.ioc.dependency.inject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuwenxue
 * @date 2022-10-22
 */
@Component
public class ExampleA {
    @Autowired
    ExampleB exampleB;
}
