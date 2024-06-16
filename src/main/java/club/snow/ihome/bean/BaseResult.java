package club.snow.ihome.bean;

import club.snow.ihome.common.enums.BusinessExceptionEnum;

import java.io.Serializable;

/**
 * The type BaseResult.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS;

    private static final Integer FAIL;

    private int code;

    private String msg;

    private T data;

    static {
        SUCCESS = BusinessExceptionEnum.SUCCESS.getCode();
        FAIL = BusinessExceptionEnum.FAIL.getCode();
    }

    public static <T> BaseResult<T> ok() {
        return restResult(SUCCESS, "操作成功", null);
    }

    public static <T> BaseResult<T> ok(T data) {
        return restResult(SUCCESS, "操作成功", data);
    }

    public static <T> BaseResult<T> ok(String msg, T data) {
        return restResult(SUCCESS, msg, data);
    }

    public static <T> BaseResult<T> fail() {
        return restResult(FAIL, "操作失败", null);
    }

    public static <T> BaseResult<T> fail(String msg, T data) {
        return restResult(FAIL, msg, data);
    }

    private static <T> BaseResult<T> restResult(int code, String msg, T data) {
        BaseResult<T> apiResult = new BaseResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
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
