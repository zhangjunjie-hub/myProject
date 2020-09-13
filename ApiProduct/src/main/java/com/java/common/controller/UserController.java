package com.java.common.controller;

import com.java.common.dto.User;
import com.java.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/user")
@RestController
public class UserController implements  UserControllerInterface {

    @Autowired
    private UserService userService;

    @PostMapping(value = "saveUser",consumes = "application/json")
    @Override
    public Map insertUser(@RequestBody(required = false) User user, HttpServletRequest request) {
        //初始化基本数据
//        String jsonStr = request.getHeader("userName");
//        user = new User();
//        user.setUserName(request.getHeader("userName"));
//        user.setPassword(request.getHeader("password"));
//        user.setStatus(request.getHeader("status"));
//        user.setRoleId(request.getHeader("roleId"));
        //设定账号的初始状态
        user.setStatus("0");
        //设定初始角色
        user.setRoleId("nomal");
        user.setCreateDate(new Date());
        user.setCreateBy("system");
        user.setUpdateDate(new Date());
        user.setUpdateBy("system");
        userService.insertUser(user);
        Map map = new HashMap();
        map.put("msg","添加成功");
       return map;
    }

    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    @Override
    public void updateUser(@RequestBody User user) {
       userService.updateUser(user);
    }

    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    @Override
    public void deleteUser(@RequestBody User user) {
       userService.deleteUser(user);
    }

    @RequestMapping(value = "findUserByInfo",method = RequestMethod.POST)
    @Override
    public User findUserByUserInfo(@RequestBody User user) {
        return userService.findUserByUserInfos(user);
    }

    @RequestMapping(value = "findAllUsers",method = RequestMethod.GET)
    @Override
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

}
