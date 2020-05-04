package ch11.controller;

import ch11.spring.MemberNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handlerDataException(){
        return "error/dataError";
    }
}
