package com.huang.shorturl.enums;


/**
 * @author yaohui.huang
 * @className StatusEnum
 * @date 2023/1/3 14:08
 * @description: 参数枚举
 */
public enum StatusEnum {
	//参数错误
    ILLEGAL_PARAMETER(420500, "参数错误"),
    //正确
	OK(200,  "OK");
    private int code;
    private String msg;

    private StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
