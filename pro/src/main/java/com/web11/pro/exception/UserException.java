package com.web11.pro.exception;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserException extends  RuntimeException {

   private String errorField;
   private String errorValue;
    public UserException(String errorField, String errorValue,String message) {
        super(message);
        this.errorField = errorField;
        this.errorValue = errorValue;
    }
}
