package club.snow.ihome.common.enums;

import lombok.Getter;

/**
 * The enum User type enum.
 * * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 *
 * @date 2024.06.26
 */
@Getter
public enum UserTypeEnum {

    SYSTEM("00"), REGISTER("01");

    private final String code;

    UserTypeEnum(String code) {
        this.code = code;
    }
}
