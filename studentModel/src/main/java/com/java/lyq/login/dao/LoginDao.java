package com.java.lyq.login.dao;

import com.java.lyq.api.interfaces.UserControllerInterface;
import com.java.lyq.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 其中value指的是调用的服务名称
 * path指的是类的requestMapping
 * @author zhangjunjie
 *
 */
@FeignClient(value = "userApi",url = "http://127.0.0.1:56929" ,path = "/user")
public interface LoginDao  {
    @RequestMapping(path = "saveUser",method = RequestMethod.POST)
    Map insertUser(@RequestBody User user);
    @RequestMapping(path = "updateUser",method = RequestMethod.POST)
    void updateUser(@RequestBody User user);
    @RequestMapping(path = "deleteUser",method = RequestMethod.POST)
    void deleteUser(@RequestBody User user);
    @RequestMapping(path = "findUserByInfo",method = RequestMethod.POST)
    User findUserByUserInfo(@RequestBody User user);
    @RequestMapping(path = "findAllUsers",method = RequestMethod.GET)
    List<User> findAllUsers();


}
