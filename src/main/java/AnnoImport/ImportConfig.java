package AnnoImport;

import AnnoImport.factoryBean.ColorFactoryBean;
import AnnoImport.importbeandefinition.ColorImportBeanDefinition;
import AnnoImport.importselector.ColorImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(value = {Color.class, Black.class}) // 导入普通类
//@Import(ConditionalConfig.class) // 支持导入配置类
// 导入 ImportSelectors,通过返回的全类名去导入加载bean组件 ， 导入ImportBeanDefinitionRegistrar加载beanDefinition
@Import({ColorImportSelector.class, ColorImportBeanDefinition.class})
@Configuration
public class ImportConfig {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        // 这里使用了FactoryBean接口 虽然返回值是ColorFactoryBean，但其实注入容器中的bean是Color
        System.out.println("调用BeanFactory#getObject方法 初始化colorFactoryBean");
        return new ColorFactoryBean();
    }
}
