package club.snow.ihome;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.HashSet;


/**
 * The type RedisTest.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.13
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedis() {
        RedisSerializer defaultSerializer = redisTemplate.getDefaultSerializer();
        RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer valueSerializer = redisTemplate.getValueSerializer();

        System.out.println(defaultSerializer);
        System.out.println(keySerializer);
        System.out.println(valueSerializer);
    }

    @Test
    public void testRedisOpsValue() {
        String key = "testRedisOpsValue:redis:test1";
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setToken("pengdahai216@126.com");
        userLoginDTO.setSignInIp("123123123");
        userLoginDTO.setUsername("");
        userLoginDTO.setPhone("");
        userLoginDTO.setEmail("");
        userLoginDTO.setPassword("");
        userLoginDTO.setUserId(0L);
        userLoginDTO.setToken("");
        userLoginDTO.setSignInTime(0L);
        userLoginDTO.setExpireTime(0L);
        userLoginDTO.setSignInIp("");
        userLoginDTO.setAuthorities(new HashSet<>());
        userLoginDTO.setAccountNonExpired(false);
        userLoginDTO.setAccountNonLocked(false);
        userLoginDTO.setCredentialsNonExpired(false);
        userLoginDTO.setEnabled(false);

        redisUtil.setCacheObject(key, userLoginDTO);

        UserLoginDTO cacheObject = redisUtil.getCacheObject(key);
        System.out.println(cacheObject);
    }
}
