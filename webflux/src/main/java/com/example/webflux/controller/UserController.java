package com.example.webflux.controller;


import com.example.webflux.bean.User;
import com.example.webflux.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping(value = "all")
    public Flux<User> getAllUser(){
        return repository.findAll();
    };

    @GetMapping(value = "/sse/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getSseUser(){
        return repository.findAll();
    };

    @PostMapping(value = "save")
    public void saveUser(@RequestBody User user){

        System.out.println(user);


    }

}
