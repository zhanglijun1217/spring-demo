package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zlj on 2019/8/10.
 */
@Component
public class Car implements InitializingBean, DisposableBean {

   public Car() {
       System.out.println("car构造方法被调用");
   }

   public void customInit() {
       System.out.println("自定义，car对象初始化方法");
   }

   public void customDestroy() {
       System.out.println("自定义，car对象销毁化方法");
   }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口，car对象初始化方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean接口，car对象销毁化方法");
    }

    @PostConstruct
    public final void postConstruct() {
        System.out.println("postConstruct注解，car对象初始化方法");
    }

    @PreDestroy
    public final void preDestroy() {
        System.out.println("preDestroy注解，car对象销毁化方法");
    }

}
