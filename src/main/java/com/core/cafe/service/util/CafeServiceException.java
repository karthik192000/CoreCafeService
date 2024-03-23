package com.core.cafe.service.util;

import org.springframework.http.HttpStatus;

public class CafeServiceException extends  RuntimeException{


    String errorMessage;

    private HttpStatus code;


    public CafeServiceException(){
        super();
    }


    public CafeServiceException(String errorMessage,HttpStatus code){
        super();
        this.errorMessage = errorMessage;
        this.code=code;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}
