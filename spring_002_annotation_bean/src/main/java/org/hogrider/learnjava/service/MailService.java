package org.hogrider.learnjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MailService {

    // 注入第三方的Bean: ZoneId，如果不存在，则使用系统默认的
    @Autowired(required = false)
    private ZoneId zoneId = ZoneId.systemDefault();

    /***
     * Spring容器会对Bean做如下初始化流程：
     *
     * 调用构造方法创建MailService实例；
     * 根据@Autowired进行注入；
     * 调用标记有@PostConstruct的init()方法进行初始化。
     */
    @PostConstruct
    public void init() {
        System.out.println("Init mail service with zoneId = " + this.zoneId);
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Shutdown mail service");
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void sendLoginMail(User user) {
        System.err.println(String.format("Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    public void sendRegistrationMail(User user) {
        System.err.println(String.format("Welcome, %s!", user.getName()));

    }
}
