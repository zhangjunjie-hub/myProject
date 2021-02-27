package com.java.lyq.login.service;

import com.java.lyq.api.interfaces.UserControllerInterface;
import com.java.lyq.dto.User;
import com.java.lyq.login.dao.LoginDao;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
    public class LoginServiceImpl implements UserControllerInterface {
  @Autowired
  private LoginDao loginDao;

    @HystrixCommand(fallbackMethod = "errorMethods",
     commandProperties = {
            //默认20，10秒内请求大于20个，就启动熔断器
            //请求符合熔断器条件，触发fallback逻辑
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),
             //当请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件就触发
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50"),
             //熔断多少秒之后去尝试请求，默认设置为5秒
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "5000")
     }
    )
    @Override
    public Map insertUser(User user, HttpServletRequest request) {
        return loginDao.insertUser(user);
    }

    private User errorMethods(User user){
        Map map = new HashMap();
        map.put("result","error");
        System.out.println("调用该服务降级方法");
        return null;
    }


    @HystrixCommand(fallbackMethod = "errorMethods",
            commandProperties = {
                    //默认20，10秒内请求大于20个，就启动熔断器
                    //请求符合熔断器条件，触发fallback逻辑
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),
                    //当请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件就触发
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50"),
                    //熔断多少秒之后去尝试请求，默认设置为5秒
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "5000")
            }
    )
    @Override
    public void updateUser(User user) {
     loginDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        loginDao.deleteUser(user);
    }
    @HystrixCommand(fallbackMethod = "errorMethods",
            commandProperties = {
                    //默认20，10秒内请求大于20个，就启动熔断器
                    //请求符合熔断器条件，触发fallback逻辑
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "10"),
                    //当请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件就触发
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "50"),
                    //熔断多少秒之后去尝试请求，默认设置为5秒
                    @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value = "5000")
            }
    )
    @Override
    public User findUserByUserInfo(User user) {
        return loginDao.findUserByUserInfo(user);
    }

    @Override
    public List<User> findAllUsers() {
        return loginDao.findAllUsers();
    }
}
