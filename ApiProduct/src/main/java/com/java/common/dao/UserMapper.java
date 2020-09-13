package com.java.common.dao;

import com.java.common.dto.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO T_USER (USERNAME,PASSWORD,ROLEID,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY,STATUS) VALUES(#{userName},#{password},#{roleId},#{createDate},#{createBy},#{updateDate},#{updateBy},#{status})")
    Integer insertUser(User user);

    @Update("UPDATE T_USER SET PASSWORD= #{password},ROLEID = #{roleId},STATUS = #{status} WHERE USERID = #{userId}")
    void updateUser(User user);

    //删除的时候，不做物理删除，只是状态变更为无效即可
    @Update("UPDATE T_USER SET STATUS = #{status} WHERE USERID = #{userId}")
    void deleteUser(User user);

    @Select("SELECT * FROM T_USER WHERE USERNAME = #{userName} AND PASSWORD = #{password}")
    @Results(id = "user",value = {
            @Result(property = "userName" ,column = "USERNAME"),
            @Result(property = "password" ,column = "PASSWORD"),
            @Result(property = "roleId" ,column = "ROLEID"),
            @Result(property = "createDate" ,column = "CREATE_DATE"),
            @Result(property = "createBy" ,column = "CREATE_BY"),
            @Result(property = "updateDate" ,column = "UPDATE_DATE"),
            @Result(property = "updateBy" ,column = "UPDATE_BY"),
            @Result(property = "status" ,column = "STATUS")
    })
    User findUser(User user);

    @Select("SELECT * FROM T_USER ")
    @ResultMap(value = "user")
    List<User> findAllUsers();





}
