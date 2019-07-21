package AnnoImport.importbeandefinition;

import AnnoImport.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ColorImportBeanDefinition implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean blue = registry.containsBeanDefinition("AnnoImport.Blue");
        boolean yellow = registry.containsBeanDefinition("AnnoImport.Yellow");

        // 如果容器中有blue和yellow组件 初始化一个rainbow bean
        if (blue && yellow) {
            // 初始化一个bean定义
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Rainbow.class);
            // 通过调用BeanDefinitionRegistry#registerBeanDefinition 方法注册一个组件
            registry.registerBeanDefinition("raimbow", beanDefinition);
        }
    }
}
