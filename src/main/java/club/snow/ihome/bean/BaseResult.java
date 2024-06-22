package club.snow.ihome.bean;

import club.snow.ihome.common.enums.BusinessInfoEnum;
import club.snow.ihome.common.utils.LocaleUtil;
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
        return restResult(SUCCESS, BusinessInfoEnum.SUCCESS.getMessage(), null);
    }

    public static <T> BaseResult<T> ok(T data) {
        return restResult(SUCCESS, BusinessInfoEnum.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResult<T> fail() {
        return restResult(FAIL, BusinessInfoEnum.FAIL.getMessage(), null);
    }

    public static <T> BaseResult<T> fail(String msg) {
        return restResult(FAIL, msg, null);
    }

    public static <T> BaseResult<T> fail(Integer code, String msg, Object... args) {
        return restResult(code, msg, null, args);
    }

    private static <T> BaseResult<T> restResult(int code, String msg, T data, Object... args) {
        BaseResult<T> apiResult = new BaseResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(LocaleUtil.get(msg, args));
        apiResult.setData(data);
        return apiResult;
    }
}
