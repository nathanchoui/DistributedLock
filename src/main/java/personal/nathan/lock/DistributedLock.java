package personal.nathan.lock;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/1/3.
 */
public interface DistributedLock {

    /**
     * 尝试给资源加锁
     *
     * @param lockName 锁名称
     * @param tryLockTimeout 尝试的加锁时间
     * @param timeout
     * @return
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
