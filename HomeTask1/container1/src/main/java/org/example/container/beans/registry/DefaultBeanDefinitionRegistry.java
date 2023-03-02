package org.example.container.beans.registry;

import org.example.container.beans.BeanDefinition;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBeanDefinitionRegistry implements BeanDefinitionRegistry{

    Map<String, BeanDefinition> definitionMap = new HashMap<>();



    @Override
    public void registerBeanDefinition(BeanDefinition definition) {
        definitionMap.put(definition.getId(),definition);
    }

    @Override
    public List<BeanDefinition> find(Class calzz) {
        return definitionMap.values()
                .stream()
                .filter(bd->calzz.isAssignableFrom(bd.getClazz()))
                .collect(Collectors.toList());
    }

    @Override
    public BeanDefinition find(String id) {
        return definitionMap.get(id);
    }

    @Override
    public Set<String> getBeanIds() {
        return definitionMap.keySet();
    }
}
