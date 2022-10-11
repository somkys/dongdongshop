package com.dongdongshop.enume;

public enum ResuleMenu {

    SUCCESS(10000, "操作成功"),

    ERROR(99999, "操作失败");

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
