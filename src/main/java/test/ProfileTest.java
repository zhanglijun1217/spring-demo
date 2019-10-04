package test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import profile.ProfileConfig;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by zlj on 2019/10/4.
 */
public class ProfileTest {

    @Test
    public void testProfile() {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ProfileConfig.class);


        // 注意这里如果上面初始化application时用的是有参数构造函数，这里addActiveProfile或者setActiveProfile是不生效的
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.getEnvironment().setActiveProfiles("dev", "prod");
        configApplicationContext.register(ProfileConfig.class);
        // 在invoke之前要刷新bean 其实去看有参数构造函数也是做得这些事
        configApplicationContext.refresh();

        configApplicationContext.start();


        // 得到DataSourced對應的bean
        String[] beanNamesForType = configApplicationContext.getBeanNamesForType(DataSource.class);

        Arrays.stream(beanNamesForType).forEach(System.out::println);

        configApplicationContext.close();
    }
}
