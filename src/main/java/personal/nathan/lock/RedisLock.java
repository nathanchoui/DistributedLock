package personal.nathan.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/1/3.
 */
@Component
public class RedisLock implements DistributedLock {

    /**
     * redis key的前缀，与lockName，组成唯一键
     */
    private static final String LOCK_KEY_PREFIX = "lock:";

    /**
     * 尝试加锁的最大时间
     */
    private static final int MAX_TRYLOCKTIMEOUT = 1000;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String tryLock(String lockName, long tryLockTimeout, long timeout) {
        String redisKey = getRedisKey(lockName);
        if (tryLockTimeout > MAX_TRYLOCKTIMEOUT) {
            tryLockTimeout = MAX_TRYLOCKTIMEOUT;
        }
        long endTime = System.currentTimeMillis() + tryLockTimeout;
        // 返回此次锁的id
        String id = UUID.randomUUID().toString();
        while (System.currentTimeMillis() < endTime) {
            // 开启事务
            redisTemplate.multi();
            // 底层用到setnx，如过存在则不set
            redisTemplate.opsForValue().setIfAbsent(redisKey, id);
            // 设置超时时间
            redisTemplate.expire(redisKey, timeout, TimeUnit.MILLISECONDS);
            // 执行事务
            redisTemplate.exec();
            List<Object> resList = redisTemplate.exec();
            if (resList != null
                    && !resList.isEmpty()) {
                resList.forEach(System.out::println);
                return id;
            }

//            boolean lockFlag = redisTemplate.opsForValue().setIfAbsent(redisKey, id);
//            // 加锁成功
//            if (lockFlag) {
//                // FIXME 在setnx 和 expire之间可能会存在机器崩溃，导致没有设置到超时时间，从而导致死锁。
//
//                redisTemplate.expire(redisKey, timeout, TimeUnit.MILLISECONDS);
//                // 返回次锁的id
//                return id;
//            }

            // 加锁失败则自旋10ms后再次尝试
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // https://blog.csdn.net/ww464214415/article/details/80324013
        return null;
    }

    @Override
    public boolean unlock(String lockName, String identifier) {
        String redisKey = getRedisKey(lockName);
        redisTemplate.watch(redisKey);
        redisTemplate.multi();
        if (identifier.equals(redisTemplate.opsForValue().get(redisKey))) {
            redisTemplate.delete(redisKey);
        }
        List<Object> resList = redisTemplate.exec();
        if (resList != null
                && !resList.isEmpty()) {
            resList.forEach(System.out::println);
            return true;
        }
        return false;
    }

    private String getRedisKey(String lockName) {
        return LOCK_KEY_PREFIX + lockName;
    }
}
