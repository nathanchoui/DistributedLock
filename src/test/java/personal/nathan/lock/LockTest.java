package personal.nathan.lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import personal.nathan.DistrutedLockApp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by za-zhangwei002 on 2019/1/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DistrutedLockApp.class})
public class LockTest {

    @Autowired
    private BuySth buySth;

    @Test
    public void test() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<Future<String>> resList = new ArrayList<>(300);
        for (int i = 0; i < 200; i++) {
            Future<String> res = executorService.submit(buySth);
            resList.add(res);
        }
        resList.forEach(x -> {
            String r = "";
            try {
                r = x.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(r);
        });
    }


}
