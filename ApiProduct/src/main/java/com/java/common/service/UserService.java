package com.java.common.service;

import com.java.common.dao.UserMapper;
import com.java.common.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer insertUser(User user){
      return userMapper.insertUser(user);
    };

    public void updateUser(User user){
       userMapper.updateUser(user);
    }

    public void deleteUser(User user){
        userMapper.deleteUser(user);
    }

    public User findUserByUserInfos(User user){
       return userMapper.findUser(user);
    }

    public List<User> findAllUsers(){
        return userMapper.findAllUsers();
    }

}
