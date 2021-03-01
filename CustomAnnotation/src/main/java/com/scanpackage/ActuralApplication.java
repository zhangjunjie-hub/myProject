package com.scanpackage;

import com.EcltAnnotation.EctBeanAnnotation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ActuralApplication implements ApplicationInterface {

    /** The package separator character: '.' */
    private static final char PACKAGE_SEPARATOR = '.';
    /** The CGLIB class separator: "$$" */
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    /** The inner class separator character: '$' */
    private static final char INNER_CLASS_SEPARATOR = '$';

    private String xmlPath;
    /**
     * 定义bean的初始容器
     * 这里放置的value是类的实例，避免在依赖注入和直接通过getBean方法获取的对象不一致
     */
    private Map<String,Object> application = null;

    /**
     * 定义使用自定义注解的初始容器
     * 这里放置的value是类的实例，避免在依赖注入和直接通过getBean方法获取的对象不一致
     */
    private Map<String,Object> annoApplication = null;


    public ActuralApplication(String xmlPath) throws Exception {
        //对传入的包名进行判断，如果为空，则默认扫描全文件
        if(StringUtils.isBlank(xmlPath)){
            xmlPath = "com";
        }
        this.xmlPath = xmlPath;
        //这里还要初始化bean容器
        application = new ConcurrentHashMap<>();
        annoApplication = new ConcurrentHashMap<>();
        //调用扫包方法，获取对应的类路径
        Set<Class<?>> classes = ClassScannerUtils.searchClasses(xmlPath);
        //获取到所有包下的class文件之后，根据class文件是否有注解进行初始化
        initApplication(classes);
    }

    /**
     * 根据获取到的class文件对类进行初始化操作
     * @param classes
     */
     private void  initApplication(Set<Class<?>> classes) throws Exception {
     if(!classes.isEmpty()){
         Iterator<Class<?>> iterator = classes.iterator();
         while(iterator.hasNext()){
             Class<?> targetClass = iterator.next();
         //判断是否是接口，如果是的话，不实例化
             if(!isInterface(targetClass)){
             //然后进行对应的初始化
             initNomalBean(targetClass);
             //初始化带有自定义注解的bean
             initAnnotationBean(targetClass);
             }
         }
     }else{
         throw new Exception("包下无对应的class文件");
     }
     }

    /**
     * 这个是用来处理带有注解的类
     * @param targetClass
     */
    private void initAnnotationBean(Class<?> targetClass) throws IllegalAccessException, InstantiationException {
        EctBeanAnnotation declaredAnnotation = targetClass.getDeclaredAnnotation(EctBeanAnnotation.class);
        if(null != declaredAnnotation){
            annoApplication.put(getShortName(targetClass),targetClass.newInstance());
        }

    }

    /**
     * 这个是用来初始化所有的class
     * @param targetClass
     */
    private void initNomalBean(Class<?> targetClass) throws IllegalAccessException, InstantiationException {
       application.put(getShortName(targetClass),targetClass.newInstance());
    }

    /**
     * 根据类文件获取类名
     * 开头是小写的
     * @param targetClass
     * @return
     */
    private String getShortName(Class<?> targetClass){
            //用于放置类名首位
            char [] chars = new char[1];
            String className = targetClass.getTypeName();
            Assert.hasLength(className, "Class name must not be empty");
            int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
            int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
            if (nameEndIndex == -1) {
                nameEndIndex = className.length();
            }
            String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
            shortName = shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
            chars[0] = shortName.charAt(0);
            if(chars[0] >= 'A' && chars[0] <= 'Z'){
                String temp = new String(chars);
                String newShortName = shortName.replaceFirst(temp,temp.toLowerCase());
                return newShortName;
            }
            return shortName;
            }

    /**
     * 判断目标class是否是接口
     * @param targetClass
     * @return
     */
    private boolean isInterface(Class<?> targetClass) {
        return Modifier.isInterface(targetClass.getModifiers());
    }


    /**
     * 根据beanid获取对应的实例对象
     * @param beanId
     * @return
     */
    @Override
    public Object getBean(String beanId) {
        return application.get(beanId);
    }

    /**
     * 获取指定是否含有注解修饰的bean
     * @param beanId
     * @param isAnnoBean
     * @return
     */
    @Override
    public Object getBean(String beanId, boolean isAnnoBean) {
         if(isAnnoBean){
            return getAnnoBean(beanId);
         }else{
            return getBean(beanId);
         }
    }

    /**
     * 获取注解修饰的指定bean
     * @param beanId
     * @return
     */
    @Override
    public Object getAnnoBean(String beanId) {
        return annoApplication.get(beanId);
    }
}
