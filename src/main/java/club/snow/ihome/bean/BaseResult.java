package club.snow.ihome.bean;

import club.snow.ihome.common.enums.BusinessInfoEnum;
import lombok.Data;

/**
 * The type BaseResult.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
@Data
public class BaseResult<T> {

    private static final Integer SUCCESS;

    private static final Integer FAIL;

    private int code;

    private String msg;

    private T data;

    static {
        SUCCESS = BusinessInfoEnum.SUCCESS.getCode();
        FAIL = BusinessInfoEnum.FAIL.getCode();
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

    public static <T> BaseResult<T> fail(String msg) {
        return restResult(FAIL, msg, null);
    }

    public static <T> BaseResult<T> fail(Integer code, String msg) {
        return restResult(code, msg, null);
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
}
