package club.snow.ihome.common.handler;

import club.snow.ihome.bean.BaseResult;
import club.snow.ihome.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Global exception handler.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.20
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截未知异常
     *
     * @param e       the e
     * @param request the request
     * @return the base result
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseResult<Object> processException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return BaseResult.fail("系统异常，请联系管理员！");
    }

    /**
     * 拦截业务异常
     *
     * @param e       the e
     * @param request the request
     * @return the base result
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult<Object> handleServiceException(BusinessException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生业务异常.", requestURI, e);
        return BaseResult.fail(e.getCode(), e.getMessage());
    }

}
