package club.snow.ihome.common.enums;

import lombok.Getter;

/**
 * The type SignTypeEnum.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.22
 */
@Getter
public enum SignTypeEnum {

    USERNAME(0, "用户名"),

    EMAIL(1, "邮箱");

    private final Integer code;

    private final String type;

    SignTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getByCode(Integer code) {
        for (SignTypeEnum signTypeEnum : SignTypeEnum.values()) {
            if (signTypeEnum.getCode().equals(code)) {
                return signTypeEnum.getType();
            }
        }
        return "";
    }
}
