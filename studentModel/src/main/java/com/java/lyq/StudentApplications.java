package com.java.lyq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author lenovo
 */
@SpringBootApplication
@ServletComponentScan
//开启eureka客户端
@EnableEurekaClient
//开启feign
@EnableFeignClients(basePackages = "com.java.lyq")
//开启熔断器
@EnableCircuitBreaker
public class StudentApplications {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplications.class, args);
    }



}
