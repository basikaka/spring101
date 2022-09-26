package org.hogrider.learnjava;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hogrider.learnjava.service.User;
import org.hogrider.learnjava.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.ZoneId;

@Configuration
@ComponentScan
@PropertySource("classpath:/app.properties")
public class AppConfig {

    // 创建一个Bean: 这是引入第三方Bean
    @Bean
    ZoneId createZoneId( @Value("${app.zoneid:Z}") String zoneId) {
        return ZoneId.of(zoneId);
    }

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("JohnSon@example.com", "password" );
        System.out.println(user.getName());
//        AppService appService =  context.getBean(AppService.class);
//        appService.logoPrint();

    }
}