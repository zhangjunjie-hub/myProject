package com.java.lyq.dao;

import com.java.lyq.dto.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO T_STUDENT (NAME,SEX,CLASSID,STUDENTCARDNO) VALUES(#{name},#{sex},#{classId},#{studentCardNo})")
    void insertStudent(Student student);
    @Select("SELECT * FROM T_STUDENT")
    void getAllStudents();


}
