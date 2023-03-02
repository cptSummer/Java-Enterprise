package org.example;

import lombok.AllArgsConstructor;
import org.example.container.annotations.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class XService {

    private List<String> users;
    private XSender xSender;

    public void sendAll(String massage){
        for (String user:users) {
            xSender.send(user,massage);
        }

    }
}
