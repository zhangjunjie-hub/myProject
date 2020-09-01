package com.java.context;

import com.java.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@PropertySource({"classpath:/application.properties"})
@Lazy
@ComponentScan(value = {"com.java"},excludeFilters = {@ComponentScan.Filter({Controller.class})})
public class SpringConfiguration01 {

}
