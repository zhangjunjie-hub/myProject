package com.java.lyq.dto;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Student extends BaseRowModel {

    private String id;
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "性别",index = 7)
    private String sex;
    private String classId;
    @ExcelProperty(value = "身份证号",index = 1)
    private String studentCardNo;

    /**
     * 这个是后来新添加的字段
     * 添加的是父母的姓名、身份证号以及现任住址
     */
    @ExcelProperty(value = "父亲姓名",index = 2)
    //父亲姓名
    private String fatherName;
    @ExcelProperty(value = "父亲身份证号",index = 3)
    //父亲身份证号
    private String fatherCard;
    @ExcelProperty(value = "母亲姓名",index = 4)
    //母亲姓名
    private String motherName;
    @ExcelProperty(value = "母亲身份证号",index = 5)
    //母亲身份证号
    private String motherCard;
    @ExcelProperty(value = "现在住址",index = 6)
    //现任住址
    private String localAddress;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherCard() {
        return fatherCard;
    }

    public void setFatherCard(String fatherCard) {
        this.fatherCard = fatherCard;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherCard() {
        return motherCard;
    }

    public void setMotherCard(String motherCard) {
        this.motherCard = motherCard;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

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
