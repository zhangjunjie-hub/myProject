package com.java.service;

import com.EcltAnnotation.EctBeanAnnotation;
import com.EcltAnnotation.EctResource;
import com.java.dao.AppDaoImpl;

@EctBeanAnnotation
public class AppServiceImpl implements AppService {

  @EctResource
  private AppDaoImpl appDaoImpl;

    @Override
    public void add() {
      appDaoImpl.add();
    }
}
