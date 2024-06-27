package club.snow.ihome.service;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.common.config.IHomeConfig;
import club.snow.ihome.common.constants.CacheConstants;
import club.snow.ihome.common.constants.CommonConstants;
import club.snow.ihome.common.utils.IdUtil;
import club.snow.ihome.common.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * The type Token service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.24
 */
@Component
public class TokenService {

    private final String header = IHomeConfig.getToken().getHeader();

    private final Long expireTime = IHomeConfig.getToken().getExpireTime(); // 分钟

    private final String secret = IHomeConfig.getToken().getSecret();

    private static SecretKey key;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        // 根据配置文件配置生成SecretKey
        key = Jwts.SIG.HS256.key().random(new SecureRandom(secret.getBytes(StandardCharsets.UTF_8))).build();
    }

    /**
     * Create token map.
     *
     * @param userLoginDTO the user login dto
     * @return the map
     */
    public Map<String, Object> createToken(UserLoginDTO userLoginDTO) {
        String cacheToken = IdUtil.randomUUID();
        userLoginDTO.setToken(cacheToken);
        // 刷新token
        refreshToken(userLoginDTO);
        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(CommonConstants.USER_KEY, cacheToken);
        claimsMap.put(CommonConstants.DETAILS_USER_ID, userLoginDTO.getUserId());
        claimsMap.put(CommonConstants.DETAILS_USER_NAME, userLoginDTO.getUsername());
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    /**
     * Gets user info.
     *
     * @param request the request
     * @return the user info
     */
    public UserLoginDTO getUserInfo(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Claims claims = parseToken(token);
        String uuid = (String) claims.get(CommonConstants.USER_KEY);
        String userKey = getCacheTokenKey(uuid);
        return redisUtil.getCacheObject(userKey);
    }

    /**
     * Verify token.
     *
     * @param userInfo the user info
     */
    public void verifyToken(UserLoginDTO userInfo) {
        long expireTime = userInfo.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= CommonConstants.MILLIS_MINUTE_TEN) {
            refreshToken(userInfo);
        }
    }

    /**
     * Sing out boolean.
     *
     * @return the boolean
     */
    public Boolean singOut() {

        return Boolean.TRUE;
    }

    // 创建token
    private String createToken(Map<String, Object> claims) {
        Date now = new Date();
        long expireTimeMillis = expireTime * CommonConstants.MILLIS_MINUTE;
        JwtBuilder jwtBuilder = Jwts.builder()
                .id("p7i") // id
                .issuer("i-Home Developer") // 签发者
                .claims(claims) // 加密数据
                .subject("i-Home sign in.") // 主题
                .issuedAt(now) // 签发时间
                .expiration(new Date(now.getTime() + expireTimeMillis)) // 过期日期
                .signWith(key);// 签名
        jwtBuilder.header().add("JWT", "i-Home/snow");
        return CommonConstants.TOKEN_PREFIX + jwtBuilder.compact();
    }

    // 获取token
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotBlank(token) && token.startsWith(CommonConstants.TOKEN_PREFIX)) {
            token = token.replace(CommonConstants.TOKEN_PREFIX, "");
        }
        return token;
    }

    // 刷新token
    private void refreshToken(UserLoginDTO userLoginDTO) {
        userLoginDTO.setSignInTime(System.currentTimeMillis());
        userLoginDTO.setExpireTime(userLoginDTO.getSignInTime() + expireTime * 60 * 1000L);
        // 根据uuid将loginUser缓存
        String userKey = getCacheTokenKey(userLoginDTO.getToken());
        redisUtil.setCacheObject(userKey, userLoginDTO, expireTime, TimeUnit.MINUTES);
    }

    // 获取缓存key
    private String getCacheTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

    // 解析token
    private Claims parseToken(String token) {

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
