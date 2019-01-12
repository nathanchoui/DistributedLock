package personal.nathan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import personal.nathan.controllers.TestController;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2019/1/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private TestController testController;

    @Test
    public void test() {
//https://spring.io/guides/gs/testing-web/
    }
}
