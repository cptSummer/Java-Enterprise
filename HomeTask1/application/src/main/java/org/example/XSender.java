package org.example;

import org.example.container.annotations.Component;


public class XSender {

    void send(String email, String massage){
        System.out.println("Massage: " + massage + " to " + email);
    }
}
