package com.dongdongshop.utils;


import static com.dongdongshop.enume.ResuleMenu.ERROR;
import static com.dongdongshop.enume.ResuleMenu.SUCCESS;

public class Result<T> {

    public Integer code;

    public String message;

    public T data;

    public static Result Ok(){

        return new Result(SUCCESS.getCode(), SUCCESS.getMsg());
    }

    public static Result ER(){

        return new Result(ERROR.getCode(), ERROR.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
