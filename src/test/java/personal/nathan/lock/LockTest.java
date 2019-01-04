package personal.nathan.lock;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by za-zhangwei002 on 2019/1/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LockTest {

    @Autowired
    private DistributedLock lock;

    public void test() {

    }

    private void remove() {

    }
}
