package 自动装配;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by zlj on 2019/9/1.
 */
@Configuration
@ComponentScan(basePackages = {"自动装配"})
public class AutowiredConfig {


    // 如果再通过@Bean给注册一个testDao的bean实例
    @Bean
    @Primary
    public TestDao testDao2() {
        TestDao testDao = new TestDao();
        testDao.setLabel(2);
        return testDao;
    }
}
