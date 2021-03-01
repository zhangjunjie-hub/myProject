package com.scanpackage;

/**
 * @author  zhangjunjie
 */
public interface ApplicationInterface {
    /**
     * 获取某一个bean
     * @param beanId
     * @return
     */
    Object getBean(String beanId);

    /**
     *  获取指定是否有注解修饰的bean
     * @param beanId
     * @param isAnnoBean
     * @return
     */
    Object getBean(String beanId,boolean isAnnoBean);

    /**
     * 获取注解修饰的指定bean
     * @param beanId
     * @return
     */
    Object getAnnoBean(String beanId);

}
