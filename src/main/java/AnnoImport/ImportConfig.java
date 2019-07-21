package AnnoImport;

import AnnoImport.importbeandefinition.ColorImportBeanDefinition;
import AnnoImport.importselector.ColorImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(value = {Color.class, Black.class}) // 导入普通类
//@Import(ConditionalConfig.class) // 支持导入配置类
// 导入 ImportSelectors,通过返回的全类名去导入加载bean组件 ， 导入ImportBeanDefinitionRegistrar加载beanDefinition
@Import({ColorImportSelector.class, ColorImportBeanDefinition.class})
@Configuration
public class ImportConfig {
}
