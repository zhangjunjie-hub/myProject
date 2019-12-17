package com.web11.pro.service.impl;

import com.web11.pro.bean.User;
import com.web11.pro.dao.UserRepository;
import com.web11.pro.service.UserService;
import com.web11.pro.util.ValidataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public void saveUser(User user) {
        ValidataUtil.CheckName(user.getUsername());
        repository.save(user);
    }
}
