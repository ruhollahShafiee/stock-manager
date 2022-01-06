package com.eshop.stockmanager.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ErrorTemplate {
    private List<String> details;
    private Integer code;
    private String message;


    public ErrorTemplate(List<String> errors,Integer code,String message,Boolean showErrorDetails){
        this.code=code;
        this.message=message;
        this.details=(showErrorDetails ? errors:null);
    }

}
