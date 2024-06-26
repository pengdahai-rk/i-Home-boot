package club.snow.ihome.common.constants;

/**
 * The type CommonConstants.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
public class CommonConstants {
    /**
     * 用户标识
     */
    public static final String USER_KEY = "user_key";

    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名称字段
     */
    public static final String DETAILS_USER_NAME = "user_name";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 每秒钟毫秒数
     */
    public static final long MILLIS_SECOND = 1000L;

    /**
     * 每分钟毫秒数
     */
    public static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    /**
     * 每10分钟毫秒数
     */
    public static final long MILLIS_MINUTE_TEN = 10 * 60 * MILLIS_SECOND;
}
