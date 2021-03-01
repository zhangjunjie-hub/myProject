package com.scanpackage;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author  zhangjunjie
 * 这个是一个扫包工具类，
 * 用于扫描指定文件夹下的所有类
 */
public class ClassScannerUtils  {

    public static Set<Class<?>> searchClasses(String packageName) throws Exception {
        return searchClasses(packageName,null);
    }

    public static Set<Class<?>> searchClasses(String packageName, Predicate predicate) throws Exception {
        return ScanExecutor.getInstance().search(packageName,predicate);
    }










}
