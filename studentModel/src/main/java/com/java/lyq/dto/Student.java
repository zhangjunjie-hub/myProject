package com.java.lyq.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Student extends BaseRowModel {

    private String id;
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    private String sex;
    private String classId;
    @ExcelProperty(value = "身份证号（与学生编号二选一）",index = 2)
    private String studentCardNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "102";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentCardNo() {
        return studentCardNo;
    }

    public void setStudentCardNo(String studentCardNo) {
        this.studentCardNo = studentCardNo;
    }
}
