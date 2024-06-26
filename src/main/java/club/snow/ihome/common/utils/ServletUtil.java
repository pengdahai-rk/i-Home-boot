package club.snow.ihome.common.utils;

import club.snow.ihome.bean.BaseResult;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Servlet util.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
public class ServletUtil {

    /**
     * Gets param map.
     *
     * @param request the request
     * @return the param map
     */
    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }

    /**
     * 获得所有请求参数
     *
     * @param request 请求对象{@link ServletRequest}
     * @return Map
     */
    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * Gets request attributes.
     *
     * @return the request attributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * Write err msg.
     *
     * @param response the response
     * @param status   the status
     * @param errMsg   the err msg
     */
    public static void writeErrMsg(HttpServletResponse response, HttpStatus status, String errMsg) {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = response.getWriter()) {
            String jsonResponse = JsonUtil.toJSONString(BaseResult.fail(errMsg));
            writer.print(jsonResponse);
            writer.flush(); // 确保将响应内容写入到输出流
        } catch (IOException e) {
            log.error("response error fail:", e);
        }
    }

    /**
     * Url encode string.
     *
     * @param str the str
     * @return the string
     */
    public static String urlEncode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8);
    }

    /**
     * Url decode string.
     *
     * @param str the str
     * @return the string
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

}
