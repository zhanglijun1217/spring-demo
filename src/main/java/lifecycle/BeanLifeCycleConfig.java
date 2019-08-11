package lifecycle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * Created by zlj on 2019/8/10.
 */
//@ComponentScan(value = "lifecycle")
@Configuration
public class BeanLifeCycleConfig {

    //    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public Car getCar() {
        return new Car();
    }

    @Bean // 注册一个bean的后置处理器
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
