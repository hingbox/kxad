package com.example.demo;

/**
 * Created by Admin on 2018/10/17.
 */
public class CheckObject {
    private String code;
    private String message;
    private String resultcode;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CheckObject() {
    }

    public CheckObject(String code, String message) {
        this.code = code;
        this.message = message;

    }
}
