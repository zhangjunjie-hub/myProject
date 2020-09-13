package com.java.lyq.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.java.lyq.dto.Student;
import com.java.lyq.listener.ExcelListener;
import com.java.lyq.service.StudentServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController implements ApplicationContextAware {

    private ApplicationContext context;
    @Autowired
  private  StudentServiceImpl studentService;

    @RequestMapping(value = "/saveStudents",method = RequestMethod.POST)
    public String saveStudents(HttpServletRequest request, @RequestBody List<Student> list){
        studentService.insertStudent(list);
        return "保存成功";
    }

    @RequestMapping(value = "/saveStudentsFromFiles",method = RequestMethod.POST)
    public String saveStudentsFromFile(HttpServletRequest request,@RequestBody String file){
        ExcelListener excelListener = new ExcelListener(studentService);
        InputStream in = null;
        try {
            InputStream inputStream = new FileInputStream(new File(file));
             in = new BufferedInputStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelReader excelReader = new ExcelReader(in,null,excelListener);
        //读取信息
        excelReader.read(new Sheet(1,1,Student.class));
        return "保存成功";
    }

    @RequestMapping(value = "test")
    public String test(){
        String[] beanDefinitionNames = context.getBeanDefinitionNames();



        return JSON.toJSONString(beanDefinitionNames);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
