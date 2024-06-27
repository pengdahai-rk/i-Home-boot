package club.snow.ihome.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Slf4j
@Component
public class RedisUtil {

    private final RedissonClient redissonClient;

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisUtil(RedissonClient redissonClient, RedisTemplate redisTemplate) {
        this.redissonClient = redissonClient;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Gets lock.
     *
     * @param lockName the lock name
     * @param lockObj  the lock obj
     * @return the lock
     */
    public RLock getLock(String lockName, Object lockObj) {
        log.info("获取分布式锁lockName:{},businessId:{}", lockName, lockObj);
        if (ObjectUtils.isEmpty(lockName)) {
            throw new RuntimeException("分布式锁KEY为空");
        }
        if (ObjectUtils.isEmpty(lockObj)) {
            throw new RuntimeException("业务ID为空");
        }
        String lockKey = lockName + lockObj;
        return redissonClient.getLock(lockKey);
    }

    /**
     * 分布式锁实现
     *
     * @param lockName   锁名称
     * @param businessId 业务ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void lock(String lockName, Object businessId) {
        RLock rLock = getLock(lockName, businessId);
        try {
            rLock.lock();
            log.info("业务ID{}，获取锁成功", businessId);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 分布式锁实现
     *
     * @param lockName   锁名称
     * @param businessId 业务ID
     */
    public void tryLock(String lockName, Object businessId) {
        RLock rLock = getLock(lockName, businessId);
        if (!rLock.tryLock()) {
            log.info("业务ID{}，尝试获取锁失败，返回", businessId);
            return;
        }
        try {
            log.info("业务ID{}，尝试获取锁成功", businessId);
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间/不存在返回-1
     */
    public long getExpire(final String key) {
        Long expire = redisTemplate.getExpire(key);
        return Objects.isNull(expire) ? -1L : expire;
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 获得缓存的Map
     *
     * @param key Redis键
     * @return map
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return 是否成功
     */
    public boolean deleteCacheMapValue(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }


}
