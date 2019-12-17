package com.web11.pro.util;

import com.web11.pro.exception.UserException;

import java.util.stream.Stream;

public class ValidataUtil {
private static final String [] INVALIDATA_NAME={"admin","admins","root","manage"};

public static void CheckName(String userName){
    Stream.of(INVALIDATA_NAME).filter(name ->userName.equalsIgnoreCase(name))
            .findAny()
            .ifPresent(names ->{
                throw new UserException("name",names,"使用了非法的用户名");
            });
}







}
