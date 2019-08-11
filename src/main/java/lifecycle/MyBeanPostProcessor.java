package lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by zlj on 2019/8/10.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessBeforeInitialization 调用，生成beanName" + beanName + "。bean地址：" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessAfterInitialization 调用，生成beanName" + beanName + "。bean地址：" + bean);
        return bean;
    }
}
