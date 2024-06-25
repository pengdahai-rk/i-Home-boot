package club.snow.ihome.common.enums;

import lombok.Getter;

/**
 * The enum Yes or no enum.
 * * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 *
 * @date 2024.06.24
 */
@Getter
public enum YesOrNoEnum {

    YES(1), NO(0);
    
    private final Integer code;

    YesOrNoEnum(int code) {
        this.code = code;
    }
}
