package personal.nathan.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2017/12/22.
 */
@SpringBootApplication
@ComponentScan("personal.nathan")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
