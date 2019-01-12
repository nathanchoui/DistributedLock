package personal.nathan.lock;

import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/1/3.
 */
@Component
public interface DistributedLock {

    /**
     * 尝试给资源加锁
     *
     * @param lockName 锁名称
     * @param tryLockTimeout 尝试的加锁时间
     * @param timeout
     * @return 不为空-加锁成功，为空-加锁失败
     */
    String tryLock(String lockName, long tryLockTimeout, long timeout);

    /**
     *
     *
     * @param lockName
     * @param identifier
     * @return
     */
    boolean unlock(String lockName, String identifier);
}
