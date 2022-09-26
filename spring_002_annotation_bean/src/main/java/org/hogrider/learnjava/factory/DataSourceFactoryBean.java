package org.hogrider.learnjava.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Component
public class DataSourceFactoryBean implements FactoryBean<DataSource> {

    private final HikariConfig config = new HikariConfig();

    @Override
    public DataSource getObject() throws Exception {
        //HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/spring101?useUnicode=true&characterEncoding=utf-8");
        config.setUsername("root");
        config.setPassword("xu123456");
        config.addDataSourceProperty("connectionTimeout", "10000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        config.addDataSourceProperty("autoCommit", "true"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        return ds;
        //return new HikariDataSource(config);
    }

    @Override
    public Class<?> getObjectType() {
        return HikariDataSource.class;
    }

    @PostConstruct
    public void init() {
        System.out.println("dataSource init");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("dataSource closed");
    }
}