package aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Created by zlj on 2019/10/4.
 * aware 接口的测试
 *
 * Xxxaware接口提供的功能 都有一个对应的processor去实现
 *  比如ApplicationContextAware ==》 ApplicationContextAwareProcessor
 *
 *  比较简单的逻辑 比如BeanNameAware 在 org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory 抽象类中就进行了完成
 */
@Component
public class AwareBean implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("当前ioc容器" + applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字" + name);
    }


    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("StringValueResolver计算" + resolver.resolveStringValue("${os.name}"));
    }
}
