package com.java.context;


import com.java.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration01.class);
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
        Person person = context.getBean(Person.class);
        System.out.println(person);
//        String name = context.getEnvironment().getProperty("os.name");
//        System.out.println(name);

    }

}
