package test;

import lifecycle.BeanLifeCycleConfig;
import lifecycle.MyBeanPostProcessor;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by zlj on 2019/8/10.
 */
public class TestBeanLifeCycle {

    @Test
    public void testLifeCycle01() {

        // 容器启动
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);

        // 多例bean 调用才会构建bean
//        Object car = annotationConfigApplicationContext.getBean("getCar");
//
//        ConfigurableListableBeanFactory beanFactory = annotationConfigApplicationContext.getBeanFactory();
//        int beanPostProcessorCount = beanFactory.getBeanPostProcessorCount();
//        System.out.println("一共有"+ beanPostProcessorCount +"个postProcessor");
//        // 给annoApplicationContext中注册一个自己的beanPostProcessor
//        beanFactory.addBeanPostProcessor(annotationConfigApplicationContext.getBean(MyBeanPostProcessor.class));
//
//        System.out.println("一共有"+ beanPostProcessorCount +"个postProcessor");


        // 容器关闭 单例bean会自动调用销毁方法，但是多例bean不会调用销毁方法。
        annotationConfigApplicationContext.close();
    }
}
