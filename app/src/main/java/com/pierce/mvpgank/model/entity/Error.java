package com.pierce.mvpgank.model.entity;

/**
 * Author: pierce
 * Date: 2015/9/6
 */
public class Error {
    public String getMessage() {
        return message;
    }
    public Error(String s){message=s;}

    private String message;
}
