package com.onezed.learn.security.common;

/**
 * <p/>
 * Describe
 * <p/>
 *
 * @author lianwenjie
 */
public class ResponseResult<E> {

    private Integer code;

    private String message;

    private E data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this(code,null,null);
    }

    public ResponseResult(Integer code, String message) {
        this(code,message,null);
    }

    public ResponseResult(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <E> ResponseResult<E> success() {
        return new ResponseResult<>(ResponseEnum.OK.code(),ResponseEnum.OK.getDescription(),null);
    }

    public static <E> ResponseResult<E> success(String message) {
        return new ResponseResult<>(ResponseEnum.OK.code(),message,null);
    }

    public static <E> ResponseResult<E> success(String message, E data) {
        return new ResponseResult<>(ResponseEnum.OK.code(),message,data);
    }

    public static <E> ResponseResult<E> failed(String message) {
        return new ResponseResult<>(ResponseEnum.INTERNAL_SERVER_ERROR.code(),message,null);
    }

    public static <E> ResponseResult<E> failed(Integer code,String message) {
        return new ResponseResult<>(code,message,null);
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

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
