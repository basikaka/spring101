package org.hogrider.learnjava;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class AppService {

    @Value("classpath:/logo.txt")
    private Resource resource;

    @Value("classpath:/app.properties")
    private Resource propertiesResource;

    private String logo;

    private String version;

    //在 Bean 初始化的时候，进行的一些资源加载操作
    @PostConstruct
    public void init() throws IOException {
        try (var reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            this.logo = reader.lines().collect(Collectors.joining("\n"));
        }

        try (var reader = new BufferedReader(
                new InputStreamReader(propertiesResource.getInputStream(), StandardCharsets.UTF_8))) {
            this.version = reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public void logoPrint(){
        System.out.println(logo);
        System.out.println(version);
    }
}