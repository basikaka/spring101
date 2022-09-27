package org.hogrider.learnaop;


import org.springframework.stereotype.Component;

@Component
public class Alien {

    private final String name = "EVANS";

    public void show(){
        System.out.println("Hello ALIEN·" + name);
    }
}
