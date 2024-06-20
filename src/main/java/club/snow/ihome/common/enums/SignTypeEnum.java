package club.snow.ihome.common.enums;

/**
 * The type SignTypeEnum.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.22
 */
public enum SignTypeEnum {

    USERNAME(0, "用户名"),

    EMAIL(1, "邮箱");

    private final Integer code;
    private final String info;

    SignTypeEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static String getInfo(Integer code) {
        for (SignTypeEnum signTypeEnum : SignTypeEnum.values()) {
            if (signTypeEnum.getCode().equals(code)) {
                return signTypeEnum.getInfo();
            }
        }
        return null;
    }
}
