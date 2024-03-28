package com.ywm.model.base;


import com.ywm.enums.ResultEnum;

/**
 * 功能描述：接口调用结果封装类，包含操作结果码，操作结果文言，返回数据体
 *
 * @Author: ywm
 * @Date: 2021/6/22 14:26
 */
public class BaseResult<T> extends BaseData {

    private boolean success;
    private int code;
    private String msg;
    private T data;


    /**
     * 返回成功，传入返回体具体出參
     * @param data 数据文言
     */
    public static <T> BaseResult<T> success(T data){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    /**
     * 返回成功，传入返回体具体出參
     * @param msg 描述
     * @param data 数据文言
     */
    public static <T> BaseResult<T> success(String msg, T data){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回失败信息
     * @param msg 异常信息
     */
    public static <T> BaseResult<T> failure(String msg){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(ResultEnum.FAILURE.getCode());
        if(msg == null || msg.isEmpty()){
            msg = ResultEnum.SERVER_EXCEPTION.getMsg();
        }
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 返回失败信息
     * @param exceptionEnum 异常信息枚举
     */
    public static <T> BaseResult<T> failure(ResultEnum exceptionEnum){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }

    /**
     * 返回自定义信息
     */
    public static <T> BaseResult<T> customize(boolean isSuccess, int code, String msg, T data){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(isSuccess);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 返回@validated参数校验异常信息
     * @param exceptionMsg 异常信息
     */
    public static <T> BaseResult<T> validatedException(String exceptionMsg){
        BaseResult<T> result = new BaseResult<>();
        result.setSuccess(false);
        result.setCode(ResultEnum.PARAMETER_ERROR.getCode());
        if(exceptionMsg == null || exceptionMsg.isEmpty()){
            exceptionMsg = ResultEnum.PARAMETER_ERROR.getMsg();
        }
        result.setMsg(exceptionMsg);
        result.setData(null);
        return result;
    }


    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean isSuccess) {
        this.success = isSuccess;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
