package com.java.lyq.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//@WebFilter(urlPatterns={"/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init ****************************************************");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String name = request.getHeader("name");
        String pwd = request.getHeader("password");
//        if((!"root".equals(name))&&(!"123456".equals(pwd))){
//            return ;
//        }
        String url = request.getRequestURL().toString();
        //if(url.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
       // }
        //放行
        //filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy *************************************");
    }
}
