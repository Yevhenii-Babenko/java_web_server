package com.javaserver.demo1;

import com.javaserver.demo1.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import  com.javaserver.demo1.database.DatabaseConnectionManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.javaserver.*")
@EnableJpaRepositories("com.javaserver.demo1.repository.UserRepository")
@ComponentScan(basePackages = "com.javaserver.demo1")
public class Demo1Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
}
