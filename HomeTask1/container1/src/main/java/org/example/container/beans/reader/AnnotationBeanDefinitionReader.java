package org.example.container.beans.reader;

import lombok.RequiredArgsConstructor;
import org.example.container.annotations.Component;
import org.example.container.annotations.Configuration;
import org.example.container.beans.BeanDefinition;
import org.example.container.beans.reader.BeanDefinitionReader;
import org.example.container.utils.PackageScanner;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AnnotationBeanDefinitionReader implements BeanDefinitionReader {
    private final String packageName;
    private final PackageScanner packageScanner;
    private final static List<Class> ANNOTATIONS = List.of(
            Component.class,
            Configuration.class
    );

    @Override
    public List<BeanDefinition> read() {
        return packageScanner.find(packageName).stream()
                .filter(this::testAnnotations)
                .map(this::toBeanDefinition)
                .collect(Collectors.toList());

    }

    private boolean testAnnotations(Class aClass) {
        return ANNOTATIONS.stream().anyMatch(ann -> aClass.isAnnotationPresent(ann));
    }

    private BeanDefinition toBeanDefinition(Class aClass) {
        return new BeanDefinition(
                aClass.getSimpleName(),
                aClass,
                null
        );

    }
}
