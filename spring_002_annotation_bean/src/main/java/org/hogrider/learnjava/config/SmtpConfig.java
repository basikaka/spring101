package org.hogrider.learnjava.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/***
 *
 * 创建一个简单的 Java Bean，获取配置文件里的属性值，
 * 然后，在其他Bean里，需要这些属性值的时候，通过注入这个 SmtpConfig 这个简单的
 * JavaBean 获取。例如，在 MailService Bean里，获取host和port的属性值，它就是
 * 通过"#{smtpConfig.host}" 这样的形式，获取。
 *
* */
@Component
@PropertySource("classpath:/app.properties")
public class SmtpConfig {

    @Value("${smtp.host}")
    private String host;
    @Value("${smtp.port}")
    private String port;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
