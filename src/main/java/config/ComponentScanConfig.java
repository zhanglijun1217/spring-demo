package config;

import componentAnno.ComponentScanDao;
import config.typeFilter.MyERTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import static org.springframework.context.annotation.FilterType.CUSTOM;

/**
 * ComponentScan注解 来代替包扫描
 *  @excludeFilters 是扫描的时候 排除哪些组件 值是Filter[]
 *  @includeFilters 是包括哪些组件 值是Filter[] 注意要禁用默认规则才生效只包含的规则
 *
 *
 * Created by zlj on 2019/6/25.
 */
@Configuration // 配置类相当于配置文件
@ComponentScans
(
        @ComponentScan(
        basePackages = "componentAnno"
//        ,// 可以排除一些的bean
//        excludeFilters = {
//                // 忽略Controller注解的bean
//                @ComponentScan.Filter(value = {Controller.class})
//
//        }
        // 只包含对应的bean 注意配置includeFilters时要 配置不使用默认设置 useDefaultFilters = false 这里是只包含 ComponentScanDao这个bean
        , includeFilters = {

                // 扫描指定类型的类
//                @ComponentScan.Filter(type = ASSIGNABLE_TYPE,value = {ComponentScanDao.class}),
                // 扫描包含自定义（包含 ‘er’ 的类）
                @ComponentScan.Filter(type = CUSTOM, value = {MyERTypeFilter.class})
        }, useDefaultFilters = false
        )
)

public class ComponentScanConfig {
}
