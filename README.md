## 注解驱动

### @Bean
- 相当于xml中的bean标签，要在配置类中（@Configuration）使用声明一个bean。
- 默认bean的名称为方法名字。
- 可以通过@Bean中的value指定bean的id。
- @Bean所在的方法的返回值就是spring bean对应的class。

### @Configuration
- 声明一个配置类，相当于xml配置，这里声明之后的类，可以通过在main方法中的AnntationConfigApplicationContext(Configuration.class)加载这个配置类。

### @ComponentScan
- 相当于xml中配置的包扫描
- @excludeFilters 是扫描的时候 排除哪些组件 值是Filter[]。
- @includeFilters 是包括哪些组件 值是Filter[] 注意要禁用默认规则才生效只包含的规则（xml中配置也一样，配置“只包含”时要配置 useDefaultFilters = false）。
- 上边两个配置中的Filter默认type是ANNOTATION，如果要使用指定类，要设置成ASSIGNABLE_TYPE。
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/componentScan.png)
#### 自定义Filter做包扫描过滤
可以看到上边@ComponentScan注解的值是Filter[]，这里要去了解下Filter中的FilterType：
1. FilterType.ANNOTATION 根据注解去扫描bean
2. FilterType.ASSIGNABLE_TYPE 根据指定类去扫描bean
3. FilterType.ASPECTJ 可以根据AspectJ表达式扫描bean
4. FilterType.REGEX 可以根据正则去扫描bean
5. FilterType.CUSTOM 用户自定义Filter逻辑扫描bean。
这里说下自定义Filter。
- 自定义Filter要实现TypeFilter接口，并实现其中的match方法。match方法的两个参数，MetadataReader表示当前扫描的目标类的信息；MetadataReaderFactory
是一个工厂，可以获取任何类的信息。
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/MetadataReader.png)
- 在获取类的信息（可以看到获取annotation注解信息、获取class信息、获取资源信息）后，可以根据一定的规则去过滤包扫描，这里指定的FilterType就是
CUSTOM，class就是实现了TypeFilter接口的实现类。
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/MyERTypeFilter.png)
- 如果在自定义Filter要扫描的bean在之前被别的Filter处理过，那么这个bean不会再被这个自定义Filter扫描到。

### @Scope
- spring ioc容器中的bean默认都是单例的，@Scope注解可以指定bean的作用范围。
- @Scope可以取四个值：单例、多例、web环境下的request和session
- 单例情况下，在ioc容器创建会调用bean创建方法；而在多实例的情况下，ioc容器启动不会调用创建方法创建对象，只有在获取bean的时候才会创建bean

### @Lazy
- 单实例bean在ioc容器启动的时候创建对象，也可以通过@Lazy做到懒加载，懒加载就是容器启动的时候不去创建bean对象，只有在第一次获取这个bean的时候
再创建这个bean对象。

### @Conditional
- @Conditional注解是配合初始化bean使用的，用处就是满足一定条件来控制bean的创建。@Conditional可以用在类上也可以用在方法上。当用在类上
配合@Component使用，在方法上配合@Bean使用。
- @Conditional的值是Condition接口数组，即可以配置多个Condition的实现类，作为bean初始化的组合条件。Condition接口中有match方法，实现其match方法即可
配置bean的初始化条件。match方法的参数 ConditionContext 可以获取到很多上下文信息：
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/condition%E4%B8%8A%E4%B8%8B%E6%96%87.png)
- @Conditional是Spring Boot底层使用很多的一个注解。
- 这里的测试是根据当前运行环境系统的os.name来的，更改为别的可以通过修改junit的vm参数实现：-Dos.name=windows

### @Import
回顾下给容器中注册bean组件的方法：
1. 包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）这样的方式局限性在于只能注册自己写的类
2. @Bean 经常用做导入第三方包的类作为bean，但是要在@Bean方法中实现这个初始化bean。
3. @Import 快速给容器中导入一个bean组件
4. 使用FactoryBean(工厂bean)初始化往容器中注册bean

- @Import是一个类注解，value是一个class数组。支持导入@Configuration配置类，ImportSelector和ImportBeanDefinitionRegistrar，在4.2之后的版本
也支持导入一个普通类。
下面是@Import使用的三种方式：
（1）导入的普通类，可以看到导入的bean名称默认为全类名
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/WX20190721-182711.png)
（2）使用ImportSelector。ImportSelector是一个接口，其中的selectImports方法返回应该导入容器中bean组件的全类名称数组，参数是AnnotationMetadata，即为
注解的相关信息，包括配合@Import使用的@Configuration注解信息。另外实现接口的这个方法不要返回null，spring会调用数组长度，会有NPE出现。实现好自己的Import
Selector之后，在@Import中注入即可。比如这里我还想通过selector的方式去得到新的颜色组件，可以这样将全类名返回即可：
```
public class ColorImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"AnnoImport.Blue", "AnnoImport.Yellow"};
    }
}
```
当前配置中的bean组件：
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/ImportSelector.png)
ImportSelector也是Spring Boot中用到的很多的一个接口。
（3）导入ImportBeanDefinitionRegistrar
通过实现ImportBeanDefinitionRegistrar接口，可以为bean定义注册器中注册一个bean的定义，从而实现向spring环境中导入bean。
ImportBeanDefinitionRegistrar接口中有一个registerBeanDefinitions方法，其中参数有一个BeanDefinitionRegistry，这个接口中的
registerBeanDefinition方法即可注册bean。下面代码示例 通过实现ImportBeanDefinitionRegistrar接口来达到注册rainbow bean组件的功能
```$java
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

```
在@Import中导入这个ImportBeanDefinitionRegistrar接口实现类即可。可以看到因为刚才用ImportSelector注册进去了blue和yellow组件，所以
现在容器中是有一个rainbow bean的：
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/importBeanDefinition.png)
#### FactoryBean接口
Spring提供了FactoryBean去注册一个bean组件，当你实现了接口中的getObject、getType和isSingleton方法之后，就可以用@Bean在配置中注入一个工厂bean。
Spring也有其他拓展了一些方法的FactoryBean接口，比如SmartFactoryBean。
- 注意 虽然你在@Bean方法返回值返回的是FactoryBean，但当你在使用这个bean的时候，其实是你定义的FactoryBean中的泛型类对应的bean，而不是FactoryBean这个类型
要想得到FactoryBean，可以通过获取bean名称为 &factoryBean的bean，即为真正bean对象的工厂bean对象。这个其实可以从BeanFactory类中看到这个工厂bean的前缀。
```
public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean#方法被调用.......");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        // 单例
        return true;
    }
}

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        // 这里使用了FactoryBean接口 虽然返回值是ColorFactoryBean，但其实注入容器中的bean是Color
        System.out.println("调用BeanFactory#getObject方法 初始化colorFactoryBean");
        return new ColorFactoryBean();
    }
    
    test类
        @Test
        public void testFactoryBean() {
            ApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
    
            Color bean = context.getBean(Color.class);
    
            Object colorFactoryBean = context.getBean("colorFactoryBean");
            // 单例 color bean对象
            System.out.println("color bean对象是否相等："  + (bean == colorFactoryBean));
    
            // 获取color bean对象对应的 FactoryBean 这里bean的名称是 &colorFactoryBean
            Object factoryBean1 = context.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "colorFactoryBean");
            // 用beanClass获取factoryBean 工厂bean
            ColorFactoryBean factoryBean2 = context.getBean(ColorFactoryBean.class);
            // &colorFactoryBean 为color对象的 工厂bean
            System.out.println("colorFactoryBean对象是否相等 ：" + (factoryBean1 == factoryBean2));
       }
```
test类的输出：
![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/%E5%B7%A5%E5%8E%82bean%E8%BE%93%E5%87%BA.png)


### bean的生命周期
bean的生命周期也就是bean的构建、初始化、和销毁。而管理bean的生命周期指的是容器bean可以调用我们自定义的初始化和销毁方法。
其实还有一些对一些特殊接口的判断和context初始化的过程：https://www.cnblogs.com/davidwang456/p/5632832.html

1. bean的构建：
- 单例bean是在容器启动的时候创建的
- 多例bean是在每次获取的时候创建的

2. bean的初始化
- bean构建完成，并且赋值完之后，再进行bean初始化

3. bean的销毁
- 单例bean，在容器关闭的时候自动销毁
- 多例bean，容器不会自动调用其销毁方法进行销毁。

#### 管理bean方式：
1. bean标签或者@Bean中的init-method和destroy-method
2. 使用InitializingBean接口中的方法定义bean初始化且属性被设置之后的事情，使用DisposableBean接口中的方法来定义bean销毁时做的事情。
    - 注意这里和@Bean之中的init-method和destroy-method的顺序是 InitializingBean和DisposableBean接口 早于 init-method和destroy-method方法执行。
3. 可以使用JSR250规范中的两个注解: @PostConstruct 和 @PreDestroy
    - 注意这里的顺序是 @PostConstruct 和 @PreDestroy 定义的初始化和销毁方法是早于InitializingBean和DisposableBean接口
4. spring提供了一个管理bean的bean后置处理器接口：BeanPostProcessor接口，这个接口中的两个方法：
    - postProcessBeforeInitialization：在Init之前（接口注释上说明了这里的init比如是InitializingBean接口中的方法或者@Bean中的init-method方法）的处理
    - postProcessAfterInitialization：在Init之后的处理
    
    这里可以简答记录下bean后置处理器的一个原理：
    在跟到`org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)`
    方法的时候（这个方法是在设置完bean的相关属性之后执行的），处理了所有的beanPostProcessor，初始化方法之前后初始化方法之后的处理是相同的，都是循环当前的beanPostProcessor的实现类，
    然后调用其before和after方法。
    ![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/bean%E5%90%8E%E7%BD%AE%E5%A4%84%E7%90%86%E5%99%A8.png)
    其实在spring中也有beanPostProcessor的一些实现，可以看到接口的实现很多：
    ![](https://zlj1217-blog-image.oss-cn-hongkong.aliyuncs.com/bean%E5%90%8E%E7%BD%AE%E5%A4%84%E7%90%86%E5%99%A8%E5%9C%A8spring%E4%B8%AD%E7%9A%84%E5%BA%94%E7%94%A8.png)
    这里可以对几个做一个简单说明：
    - ApplicationContextAwareProcessor 是对实现接口ApplicationContextAware接口的bean做注入容器的处理。
    - AsyncAnnotationBeanPostProcessor 对我们比较熟悉的@Async注解的一个支持
    - BeanValidationPostProcessor bean校验的后置处理器
    - AutowiredAnnotationBeanPostProcessor 是autowired注入bean实例的后置处理器。