package org.hogrider.learnjava;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hogrider.learnjava.service.User;
import org.hogrider.learnjava.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.ZoneId;

@Configuration
@ComponentScan
public class AppConfig {

    // 创建一个Bean: 这是引入第三方Bean
    @Bean
    ZoneId createZoneId() {
        return ZoneId.of("UTC+8");
    }

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("JohnSon@example.com", "password" );
        System.out.println(user.getName());
    }
}