package org.example;

import org.example.container.MyAnnotationBasedMyApplicationContext;
import org.example.container.MyApplicationContext;

public class Main {

    public static void main(String[] args) {
        MyApplicationContext context = new MyAnnotationBasedMyApplicationContext(Main.class);
        XService service = context.getBean(XService.class);
        service.sendAll("Hello");

    }
}
