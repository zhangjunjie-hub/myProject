package com.scanpackage;


import java.util.Set;
import java.util.function.Predicate;

/**
 * 自定义扫包接口
 */
public interface Scan {

        String CLASS_SUFFIX = ".class";

        Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate) throws Exception;

        default Set<Class<?>> search(String packageName) throws Exception {
            return search(packageName,null);
        }

    }

