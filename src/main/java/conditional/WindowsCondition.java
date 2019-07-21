package conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 两个参数： ConditionContext是上下文信息、AnnotatedTypeMetadata 注解的元信息
        // 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 获取当前运行的环境信息
        Environment environment = context.getEnvironment();

        // 获取bean定义的注册器
        BeanDefinitionRegistry registry = context.getRegistry();

        // 判断是否为windows
        String property = environment.getProperty("os.name");
        if (!StringUtils.isEmpty(property) && property.contains("windows")) {
            return true;
        }

        return false;
    }
}
