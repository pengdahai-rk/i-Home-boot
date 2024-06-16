package club.snow.ihome.common.enums;

/**
 * The type SignTypeEnum.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.22
 */
public enum SignTypeEnum {

    OK("0", "用户名"),

    DISABLE("1", "邮箱");

    private final String code;
    private final String info;

    SignTypeEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static String getInfo(String code) {
        for (SignTypeEnum signTypeEnum : SignTypeEnum.values()) {
            if (signTypeEnum.getCode().equals(code)) {
                return signTypeEnum.getInfo();
            }
        }
        return null;
    }
}
