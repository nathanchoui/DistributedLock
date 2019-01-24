package personal.nathan.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

/**
 * Created by za-zhangwei002 on 2019/1/24.
 */
@Service
public class BuySth implements Callable<String> {

    private int n = 100;

    @Autowired
    private DistributedLock lock;

    @Override
    public String call() throws Exception {
        String id = lock.tryLock("buySth", 2000, 200);
        if (id == null) {
            System.out.println(Thread.currentThread().getName() + ": 加锁失败，无法购买");
            return "N";
        }
        if (n <= 0) {
            return "N";
        }
        System.out.println("got 1, n: " + --n);
        lock.unlock("buySth", id);
        return "Y";
    }
}
