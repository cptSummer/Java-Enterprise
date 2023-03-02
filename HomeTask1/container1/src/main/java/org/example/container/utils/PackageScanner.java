package org.example.container.utils;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageScanner {
    @SneakyThrows
    public List<Class> find(String packageName){
        String path = packageName.replace(".","/");
        File folder = new File(getClass().getClassLoader().getResource(path).toURI());

        List<Class> classes = new ArrayList<>();
        for (File file : folder.listFiles()) {
            String name = file.getName();
            if (file.isDirectory()){
                classes.addAll(find(packageName + "." + name));
            } else if (name.endsWith(".class")) {
                String className = packageName + "." + name.substring(0,name.length()-6);

                classes.add(Class.forName(className));
            }
        }
        return classes;
    }
}
