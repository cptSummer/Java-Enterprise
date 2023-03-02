package org.example.container;

import org.example.container.beans.reader.AnnotationBeanDefinitionReader;
import org.example.container.beans.reader.BeanDefinitionReader;
import org.example.container.beans.reader.CompositeBeanDefinitionReader;
import org.example.container.beans.registry.BeanDefinitionRegistry;
import org.example.container.beans.registry.DefaultBeanDefinitionRegistry;
import org.example.container.utils.PackageScanner;

import java.util.List;

public class MyAnnotationBasedMyApplicationContext implements MyApplicationContext {

    private final String packageName;

    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final BeanFactory beanFactory;

    public MyAnnotationBasedMyApplicationContext(Class baseClass){
        packageName = baseClass.getPackageName();
        beanDefinitionRegistry = new DefaultBeanDefinitionRegistry();
        beanFactory = new DefaultBeanFactory(beanDefinitionRegistry);
        init();
    }

    private void init(){
       BeanDefinitionReader beanDefinitionReader = createBeanDefinitionReader();

       beanDefinitionReader.read().forEach(beanDefinitionRegistry::registerBeanDefinition);
       beanDefinitionRegistry.getBeanIds().forEach(beanFactory::getBean);

    }

    private BeanDefinitionReader createBeanDefinitionReader() {
        PackageScanner scanner = new PackageScanner();
        BeanDefinitionReader classReader = new AnnotationBeanDefinitionReader(packageName, scanner);
        BeanDefinitionReader configReader = new AnnotationBeanDefinitionReader(packageName, scanner);
        return new CompositeBeanDefinitionReader(classReader,configReader);
    }

    @Override
    public <T> List<T> getBeans(Class<T> tClass) {
        return beanFactory.getBeans(tClass);
    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        return beanFactory.getBean(tClass);
    }

    @Override
    public Object getBean(String beanID) {
        return beanFactory.getBean(beanID);
    }
}
