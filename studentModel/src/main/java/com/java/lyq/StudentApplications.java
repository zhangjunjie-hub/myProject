package com.java.lyq;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lenovo
 */
@SpringBootApplication
public class StudentApplications {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplications.class, args);
    }



}
