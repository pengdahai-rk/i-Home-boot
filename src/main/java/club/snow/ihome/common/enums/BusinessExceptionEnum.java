package club.snow.ihome.common.enums;

/**
 * The type BusinessExceptionEnum.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
public enum BusinessExceptionEnum {
    SUCCESS(1000000, "成功"),
    FAIL(1000001, "失败");
    private final int code;
    private final String message;

    BusinessExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
