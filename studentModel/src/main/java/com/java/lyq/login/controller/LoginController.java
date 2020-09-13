package com.java.lyq.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 这个是登页面跳转
 */

@Controller
@RequestMapping("/login")
public class LoginController {
    //这个类只做页面跳转使用
    @RequestMapping(value = "/doLogin")
    public ModelAndView doLogin(){
     ModelAndView modelAndView = new ModelAndView();
     modelAndView.setViewName("index");
     return modelAndView;
    }
    @RequestMapping(value = "/loginIndex")
    public ModelAndView loginIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/doRegist")
    public ModelAndView doRegist(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("regist");
        return modelAndView;
    }





}
