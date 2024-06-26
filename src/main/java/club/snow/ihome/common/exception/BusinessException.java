package club.snow.ihome.common.exception;

import club.snow.ihome.common.enums.BusinessInfoEnum;
import lombok.Getter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Business exception.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.20
 */
@Getter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String message;

    /**
     * 错误替换
     */
    private final List<String> messageList = new ArrayList<>();

    public BusinessException(BusinessInfoEnum businessInfoEnum) {
        this.code = businessInfoEnum.getCode();
        this.message = businessInfoEnum.getMessage();
    }

    public BusinessException(BusinessInfoEnum businessInfoEnum, List<String> messageList) {
        this.code = businessInfoEnum.getCode();
        this.message = businessInfoEnum.getMessage();
        this.messageList.addAll(messageList);
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", messageList=" + messageList +
                '}';
    }
}
