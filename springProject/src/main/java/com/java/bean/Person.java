package com.java.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@ToString
@Scope("prototype")
public class Person extends BaseObject {
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
