package com.java.lyq.dao;

import com.java.lyq.dto.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO T_STUDENT (NAME,SEX,CLASSID,STUDENTCARDNO,FATHERNAME,FATHERCARD,MOTHERNAME,MOTHERCARD,LOCALADDRESS) VALUES(#{name},#{sex},#{classId},#{studentCardNo},#{fatherName},#{fatherCard},#{motherName},#{motherCard},#{localAddress})")
    void insertStudent(Student student);
    @Select("SELECT * FROM T_STUDENT")
    void getAllStudents();
    //更新学生信息
    @Update("UPDATE T_STUDENT SET SEX = #{sex},FATHERNAME = #{fatherName},FATHERCARD=#{fatherCard},MOTHERNAME = #{motherName},MOTHERCARD = #{motherCard},LOCALADDRESS = #{localAddress} WHERE `NAME` = #{name}")
    void updateStudents(Student student);

}
