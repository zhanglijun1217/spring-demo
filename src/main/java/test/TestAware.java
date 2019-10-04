package test;

import aware.AwareConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zlj on 2019/10/4.
 */
public class TestAware {

    @Test
    public void testAware() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AwareConfig.class);
        configApplicationContext.start();

        System.out.println("测试类中的ioc" +configApplicationContext);
    }
}
