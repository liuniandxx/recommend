package com.weego.common;

/**
 * @author ln
 */
public class Response<T> {
    private Integer code;

    private String status;

    private T data;

    public T getData() {
        return data;
    }

    private Response(int code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStaus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static <T> Response<T> returnSuccess(T data) {
        return new Response<T>(1, "success", data);
    }

    public static <T> Response<T> returnFail(T data) {
        return new Response<T>(0, "error", data);
    }
}
