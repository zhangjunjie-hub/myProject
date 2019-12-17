package com.mongo.mongo.controller;

import com.mongo.mongo.bean.User;
import com.mongo.mongo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserRepository repository;

    @GetMapping(value = "all")
    public List<User> getAllUser(){
        return repository.findAll();
    };

    @PostMapping(value = "save")
    public void saveUser(@RequestBody User user){
        System.out.println(user);
        repository.save(user);
    }

}
