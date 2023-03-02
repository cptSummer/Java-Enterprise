package org.example.container.beans.reader;

import org.example.container.beans.BeanDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionReader[] readers;

    public CompositeBeanDefinitionReader(BeanDefinitionReader... readers) {
        this.readers = readers;
    }

    @Override
    public List<BeanDefinition> read() {
        return Arrays.stream(readers)
                .map(BeanDefinitionReader::read)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
