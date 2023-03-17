package com.javaserver.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.javaserver.demo1.repository.UserRepository;


import java.sql.Connection;

//@EnableJpaRepositories(basePackages="com.javaserver.demo1.repository.UserRepository")
@EnableAutoConfiguration
@SpringBootApplication
public class Demo1Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
//        DbFunction db = new DbFunction();
//        Connection conn = db.connect_to_db("postgres", "postgres", "root");
//		db.createTable(conn,"employee");
//		db.insert_row(conn, "employee", "rajat", "india");
//		db.update_name(conn,"employee","rahul", "rahul1");
//		db.search_by_name(conn, "employee", "rajat");
//		db.search_by_id(conn, "employee", 4);
//		db.read_data(conn,"employee");
//		db.delete_row_by_name(conn, "employee", "rajat");
//		db.delete_row_by_id(conn, "employee", 2);
//		db.read_data(conn,"employee");
//		db.delete_table(conn, "employee");
    }
// user serv
}
