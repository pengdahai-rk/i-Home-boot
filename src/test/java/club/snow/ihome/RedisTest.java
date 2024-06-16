package club.snow.ihome;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * The type RedisTest.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/6/13
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisOpsValue(){
        String key = "testRedisOpsValue";
        UserLoginDO userLoginDO = new UserLoginDO();
        userLoginDO.setEmail("pengdahai216@126.com");
        userLoginDO.setPassword("123123123");
        redisTemplate.opsForValue().set(key, userLoginDO);

        UserLoginDO o = (UserLoginDO)redisTemplate.opsForValue().get(key);

        System.out.println(o.getEmail());
    }
}
