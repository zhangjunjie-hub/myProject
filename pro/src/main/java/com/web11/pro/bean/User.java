package com.web11.pro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @NotBlank(message = "姓名不能为空")
    private String username;
    private String phone;
    @Range(min = 25,max = 60,message = "年龄范围必须在${min}--${max}范围内")
    private Integer age;
    private String email;
}
