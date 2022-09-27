package org.hogrider.learnaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


public class AppDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext( AppConfig.class);
        context.getBean(Alien.class).show();
    }
}
