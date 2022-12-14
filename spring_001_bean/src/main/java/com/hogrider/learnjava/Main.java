package com.hogrider.learnjava;

import com.hogrider.learnjava.service.User;
import com.hogrider.learnjava.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = userService.register("JohnSon@example.com", "password", "JohnSon");
        System.out.println(user.getName());
    }
}

