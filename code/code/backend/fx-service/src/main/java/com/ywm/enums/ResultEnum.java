package com.ywm.enums;

/**
 * 功能描述：返回状态码枚举
 *
 * @Author: ywm
 * @Date: 2023/10/22 14:49
 */
public enum ResultEnum {

    /**
     * 状态信息
     */
    SUCCESS(0, "成功"),

    FAILURE(1, "失败"),

    UNKNOWN_SERVER_ERROR(2, "未知错误"),
    SERVER_EXCEPTION(3, "系统异常，请稍后重试"),

    UNENABLE_OPERATE(4, "无法操作"),

    IO_STREAM_ERROR(5, "IO流错误"),
    PARAMETER_ERROR(6, "请求参数错误"),

    UNAUTHORIZED(401, "未授权"),


    ;


    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
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
