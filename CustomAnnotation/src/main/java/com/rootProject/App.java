package com.rootProject;

import com.scanpackage.ActuralApplication;
import com.java.service.AppServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    /** The package separator character: '.' */
    private static final char PACKAGE_SEPARATOR = '.';
    /** The CGLIB class separator: "$$" */
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    /** The inner class separator character: '$' */
    private static final char INNER_CLASS_SEPARATOR = '$';
    public static void main( String[] args ) throws Exception {
          //通过ClassPathXmlApplicationContext类加载spring的xml文件获取对应的类实例
//        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("springmvc.xml");
//        AppDao appDao = (AppDao) application.getBean("appDao");
//        appDao.add();

          //现在使用自定义扫包，来获取指定包下的所有的类，并进行初始化流程
//        Set<Class<?>> classes = ClassScannerUtils.searchClasses("com.service");
//        Iterator<Class<?>> iterator = classes.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        ActuralApplication application = new ActuralApplication("com.java");
        AppServiceImpl appService = (AppServiceImpl) application.getBean("appServiceImpl");
        appService.add();


    }


}
