package org.example.container.beans.reader;

import lombok.RequiredArgsConstructor;
import org.example.container.annotations.Bean;
import org.example.container.annotations.Configuration;
import org.example.container.beans.BeanDefinition;
import org.example.container.beans.reader.BeanDefinitionReader;
import org.example.container.utils.PackageScanner;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnnotationConfigurationBeanDefinitionReader implements BeanDefinitionReader {

    private final String packageName ;
    private final PackageScanner packageScanner;

    @Override
    public List<BeanDefinition> read() {
        return packageScanner.find(packageName).stream()
                .filter(c->c.isAnnotationPresent(Configuration.class))
                .map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(m->m.isAnnotationPresent(Bean.class))
                .map(this::toBeanDefinition)
                .collect(Collectors.toList());
    }

    private BeanDefinition toBeanDefinition(Method method) {
        return new BeanDefinition(
                method.getName(),
                method.getReturnType(),
                method
        );
    }
}
