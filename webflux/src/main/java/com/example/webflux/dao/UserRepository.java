package com.example.webflux.dao;


import com.example.webflux.bean.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User,String> {






}
