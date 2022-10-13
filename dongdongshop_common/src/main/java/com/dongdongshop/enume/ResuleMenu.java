package com.dongdongshop.enume;

public enum ResuleMenu {

    SUCCESS(10000, "操作成功"),

    ERROR(99999, "操作失败"),

    SHIRO_LOGIN_ERROR(10001,"登录失败,该账号未审核"),

    SHIRO_LOGIN_SUCCESS(10002,"登录成功"),

    SHIRO_REGISTER_ERROE(10003,"注册失败"),

    SHIRO_REGISTER_SUCCESS(10004,"注册成功,请等待管理员审核"),

    SHIRO_LOGIN_ERROR_NO(10005,"登录失败,该账号审核未通过"),

    SHIRO_LOGIN_ERROR_CLOST(10006,"登录失败,该店铺已关闭");


    private int code;

    private String msg;

    ResuleMenu(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
