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