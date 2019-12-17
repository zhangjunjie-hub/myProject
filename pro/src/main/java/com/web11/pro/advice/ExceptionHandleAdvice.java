package com.web11.pro.advice;

import com.web11.pro.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
public class ExceptionHandleAdvice {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<String> exHandle(UserException ex){
        String errorField = ex.getErrorField();
        String errorValue = ex.getErrorValue();
        String msg = ex.getMessage();
        String es = msg+"【"+errorField+":"+errorValue+"】";
        return new ResponseEntity<String>(es, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<String> exHandle(MethodArgumentNotValidException ex){
      String str = exToStr(ex);
        System.out.println("str:"+str);
   return new ResponseEntity<String>(exToStr(ex), HttpStatus.BAD_REQUEST);
  }

    private String exToStr(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField()+":"+e.getDefaultMessage())
                .reduce("",((s, s2) -> s+"\n"+s2));
    }


}
