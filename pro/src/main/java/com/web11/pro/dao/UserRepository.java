package com.web11.pro.dao;


import com.web11.pro.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String> {






}
