package com.javaserver.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import  com.javaserver.demo1.database.DatabaseConnectionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.javaserver.demo1")
@Configuration
@PropertySource("classpath:application.properties")
public class Demo1Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
//        DbFunction db = new DbFunction();
//        Connection conn = db.connect_to_db("postgres", "postgres", "root");
        DatabaseConnectionManager database = new DatabaseConnectionManager();
        database.checkConnection();
    }
}
