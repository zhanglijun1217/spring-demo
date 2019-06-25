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