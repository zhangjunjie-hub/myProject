package com.java.context;

import com.java.bean.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:/application.properties"})
@Import({Person.class})
@Lazy

public class SpringConfiguration01 {

}
