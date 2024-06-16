package club.snow.ihome.service;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.common.constants.CommonConstants;
import club.snow.ihome.common.utils.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Token service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.16
 */
@Component
public class TokenService {

    @Value("${token.header}")
    private String header;

    @Value("${token.expireTime:30}")
    private int expireTime; // 分钟

    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz1234567890";
    private static final SecretKey key = Jwts.SIG.HS256.key().random(new SecureRandom(SECRET.getBytes(StandardCharsets.UTF_8))).build();

    /**
     * Create token map.
     *
     * @param userLoginDTO the user login dto
     * @return the map
     */
    public Map<String, Object> createToken(UserLoginDTO userLoginDTO) {
        String token = IdUtil.randomUUID();
        Long userId = userLoginDTO.getUserId();
        String userName = userLoginDTO.getUsername();
        userLoginDTO.setToken(token);
        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(CommonConstants.USER_KEY, token);
        claimsMap.put(CommonConstants.DETAILS_USER_ID, userId);
        claimsMap.put(CommonConstants.DETAILS_USER_NAME, userName);
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<>();
        rspMap.put("access_token", createToken(claimsMap));
        rspMap.put("expires_in", expireTime);
        return rspMap;
    }

    /**
     * singOut
     *
     * @return {@link Boolean }
     * @see Boolean
     */
    public Boolean singOut() {

        return Boolean.TRUE;
    }

    // 创建token
    private String createToken(Map<String, Object> claims) {

        Date now = new Date();
        long expireTimeMillis = expireTime * 60 * 1000L;
        JwtBuilder jwtBuilder = Jwts.builder()
                .id("123123") // id
                .issuer("snow") // 签发者
                .claims(claims) // 加密数据
                .subject("snow subject") // 主题
                .issuedAt(now) // 签发时间
                .expiration(new Date(now.getTime() + expireTimeMillis)) // 过期日期
                .signWith(key);// 签名
        jwtBuilder.header().add("JWT", "i-Home/snow");
        return jwtBuilder.compact();
    }

    // 解析token
    private Claims parseToken(String token) {

        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }
}
