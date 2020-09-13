package com.java.lyq.login.controller;

import com.java.lyq.dto.User;
import com.java.lyq.login.service.LoginServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/loginAuth")
public class AuthController {
   @Autowired
   private LoginServiceImpl loginService;


    @RequestMapping(value = "doAuthLogin")
    public String  doAuthLogin(@RequestParam String userName, @RequestParam String password){
           if(!StringUtils.isBlank(userName) && !StringUtils.isBlank(password)){
               User user = new User();
               user.setUserName(userName);
               user.setPassword(password);
               user = loginService.findUserByUserInfo(user);
               if(null != user){
                   return "success";
               }
           }
            return "error";
    }
    @RequestMapping(value = "doRegist")
    public String  doRegist(@RequestParam String userName, @RequestParam String password, HttpServletRequest request){
        if(!StringUtils.isBlank(userName) && !StringUtils.isBlank(password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            loginService.insertUser(user, request);
        }
        return "success";
    }

}
