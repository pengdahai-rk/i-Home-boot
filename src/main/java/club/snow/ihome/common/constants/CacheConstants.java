package club.snow.ihome.common.constants;

/**
 * The type Cache constants.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.24
 */
public class CacheConstants {

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;
}
