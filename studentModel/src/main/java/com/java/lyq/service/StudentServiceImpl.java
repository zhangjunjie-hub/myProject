package com.java.lyq.service;

import com.java.lyq.dao.StudentMapper;
import com.java.lyq.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class StudentServiceImpl {

   @Autowired
   private StudentMapper studentMapper;

    public void insertStudent(List<Student> students){
           if(!CollectionUtils.isEmpty(students)){
               for (Student student: students) {
                   studentMapper.insertStudent(student);
               }
           }
    };

    public void updateStudent(List<Student> students) {
        if(!CollectionUtils.isEmpty(students)){
            for (Student student: students) {
                studentMapper.updateStudents(student);
            }
        }
    }


}

