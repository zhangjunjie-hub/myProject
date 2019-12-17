package com.mongo.mongo.dao;

import com.mongo.mongo.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,String> {






}
