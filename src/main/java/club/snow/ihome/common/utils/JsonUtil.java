package club.snow.ihome.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * The type Json util.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
public class JsonUtil {

    // jackson objectMapper
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // 日期格式
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //对象的所有字段全部列入序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的格式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        //忽略 在json字符串中存在，但在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Parse object t.
     *
     * @param <T>        the type parameter
     * @param jsonString the json string
     * @param object     the object
     * @return the t
     */
    public static <T> T parseObject(String jsonString, Class<T> object) {
        T result = null;
        try {
            result = objectMapper.readValue(jsonString, object);
        } catch (Exception e) {
            log.error("JsonString转为自定义对象失败：{}", e.getMessage());
            log.error("Json string conversion obj error:", e);
        }
        return result;
    }

    /**
     * Parse object t.
     *
     * @param <T>    the type parameter
     * @param file   the file
     * @param object the object
     * @return the t
     */
    public static <T> T parseObject(File file, Class<T> object) {
        T result = null;
        try {
            result = objectMapper.readValue(file, object);
        } catch (Exception e) {
            log.error("Read json string from file error:", e);
        }
        return result;
    }

    /**
     * Parse json array t.
     *
     * @param <T>       the type parameter
     * @param jsonArray the json array
     * @param reference the reference
     * @return the t
     */
    public static <T> T parseJSONArray(String jsonArray, TypeReference<T> reference) {
        T t = null;
        try {
            t = objectMapper.readValue(jsonArray, reference);
        } catch (Exception e) {
            log.error("JSONArray conversion list or map error::", e);
        }
        return t;
    }

    /**
     * To json string string.
     *
     * @param object the object
     * @return the string
     */
    public static String toJSONString(Object object) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Object conversion json string error:", e);
        }
        return jsonString;
    }

    /**
     * To byte array byte [ ].
     *
     * @param object the object
     * @return the byte [ ]
     */
    public static byte[] toByteArray(Object object) {
        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            log.error("Object conversion byte array error:", e);
        }
        return bytes;
    }

    /**
     * Object to file.
     *
     * @param file   the file
     * @param object the object
     */
    public static void objectToFile(File file, Object object) {
        try {
            objectMapper.writeValue(file, object);
        } catch (JsonProcessingException e) {
            log.error("Object write file json parse error:", e);
        } catch (IOException e) {
            log.error("Object write file io error:", e);
        } catch (Exception e) {
            log.error("Object write file error:", e);
        }
    }

    /**
     * Parse json object json node.
     *
     * @param jsonString the json string
     * @return the json node
     */
    public static JsonNode parseJSONObject(String jsonString) {
        // JsonNode和JSONObject一样，都是JSON树形模型，只不过在jackson中，存在的是JsonNode
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.error("Json string conversion json node error:", e);
        }
        return jsonNode;
    }

    /**
     * Parse json object json node.
     *
     * @param object the object
     * @return the json node
     */
    public static JsonNode parseJSONObject(Object object) {

        return objectMapper.valueToTree(object);
    }

    /**
     * To json string string.
     *
     * @param jsonNode the json node
     * @return the string
     */
    public static String toJSONString(JsonNode jsonNode) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            log.error("JsonNode转JSONString失败：{}", e.getMessage());
        }
        return jsonString;
    }

    /**
     * New json object object node.
     *
     * @return the object node
     */
    public static ObjectNode newJSONObject() {
        // JsonNode是一个抽象类，不能实例化，创建JSON树形模型，得用JsonNode的子类ObjectNode，用法和JSONObject大同小异
        return objectMapper.createObjectNode();
    }

    /**
     * New json array array node.
     *
     * @return the array node
     */
    public static ArrayNode newJSONArray() {
        // 创建JSON数组对象，就像JSONArray一样用
        return objectMapper.createArrayNode();
    }
}
