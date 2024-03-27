package com.core.cafe.service.controller;

import com.core.cafe.service.beans.ResponseBean;
import com.core.cafe.service.util.CafeServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CafeServiceControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {CafeServiceException.class})
    protected ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request){
        CafeServiceException cafeServiceException = (CafeServiceException) ex;
        return handleExceptionInternal(ex,new ResponseBean(cafeServiceException.getErrorMessage()),new HttpHeaders(),cafeServiceException.getCode(),request);
    }
}
