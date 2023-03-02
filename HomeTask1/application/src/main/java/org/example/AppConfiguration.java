package org.example;

import org.example.container.annotations.Bean;
import org.example.container.annotations.Configuration;

import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    XService xService(XSender xSender){
        List<String> users = List.of(
                "one@i.ua",
                "two@i.ua"
        );
        return new XService(users,xSender);
    }
}
