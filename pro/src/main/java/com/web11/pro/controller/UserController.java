package com.web11.pro.controller;


import com.web11.pro.bean.User;
import com.web11.pro.dao.UserRepository;
import com.web11.pro.exception.UserException;
import com.web11.pro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;



    @GetMapping(value = "all")
    public List<User> getAllUser(){
        return repository.findAll();
    };

    @PostMapping(value = "save")
    public void saveUser(@Valid @RequestBody User user){
//        System.out.println(user);
//        ValidataUtil.CheckName(user.getUsername());
//        repository.save(user);
        service.saveUser(user);

    }

}
