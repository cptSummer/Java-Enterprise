package org.example.container.beans;

import lombok.Value;

import java.lang.reflect.Method;
@Value
public class BeanDefinition {

    String id;
    Class clazz;
    Method factoryMethod;

}
