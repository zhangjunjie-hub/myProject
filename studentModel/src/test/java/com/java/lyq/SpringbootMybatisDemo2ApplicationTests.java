package com.java.lyq;

import com.java.lyq.controller.StudentController;
import com.java.lyq.dao.UserMapper;
import com.java.lyq.dto.Student;
import com.java.lyq.dto.User;
import com.java.lyq.service.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisDemo2ApplicationTests {

 @Autowired
 public UserMapper userMapper;

 @Autowired
 private StudentServiceImpl studentService;
 @Autowired
 private StudentController controller;
@Autowired
private HttpServletRequest request;
 @Test
 public void test(){
// userMapper.insert("zhangsan","123456","15303791207");
// User user = userMapper.findUserByPhone("15303791207");
//     System.out.println(user);

//  List<Student> students = new ArrayList<>();
// for (int i=0; i<10; i++){
//  Student student = new Student();
//  student.setName("张三"+i);
//  student.setSex("男");
//  student.setClassId("102");
//  student.setStudentCardNo("41031119891105451"+i);
//  students.add(student);
// }
// studentService.insertStudent(students);

  controller.saveStudentsFromFile(request,"C:\\Users\\lenovo\\Desktop\\刘玉青\\一二班生活服务费身份证号码.xls");


 }



}
