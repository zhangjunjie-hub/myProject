package com.java.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@ToString
public class Person {
//    @Value("#{100-98.6666}")
    @Value("${person.name}")
 private String name;
    @Value("${person.address}")
 private String address;
    @Value("${person.age}")
 private Integer age;

    public Person() {
        System.out.println("person Constructor……");
    }
}
