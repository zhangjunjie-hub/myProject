package com.java.lyq.api.interfaces;


import com.java.lyq.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 这个controller作为api接口使用
 */
public interface UserControllerInterface {

     Map insertUser(User user, HttpServletRequest request);
     void updateUser(User user);
     void deleteUser(User user);
     User findUserByUserInfo(User user);
     List<User> findAllUsers();


}
