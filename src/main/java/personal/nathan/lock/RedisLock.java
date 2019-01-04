package personal.nathan.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/1/3.
 */
@Component
public class RedisLock implements DistributedLock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String tryLock(String lockName, long tryLockTimeout, long timeout) {
        // https://blog.csdn.net/ww464214415/article/details/80324013
        redisTemplate.opsForValue().setIfAbsent(lockName, "id");

        return null;
    }

    @Override
    public boolean unlock(String lockName, String identifier) {
        return false;
    }
}
