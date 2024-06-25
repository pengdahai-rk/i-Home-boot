package club.snow.ihome.common.utils;

import club.snow.ihome.bean.BaseResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * The type Servlet util.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
public class ServletUtil {


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

}
