package club.snow.ihome.common.enums;

import lombok.Getter;

/**
 * The type BusinessExceptionEnum.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
@Getter
public enum BusinessInfoEnum {
    
    SUCCESS(1000000, "success"),
    FAIL(1000001, "fail"),
    USER_SING_NOT_EXIST(2000001, "user.sign.not-exist"),
    ;
    private final int code;
    private final String message;

    BusinessInfoEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
