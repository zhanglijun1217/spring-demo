package test;

import bean.Person;
import config.PersonConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import scope.Book;
import scope.ScopeConfig;

/**
 * Created by zlj on 2019/6/25.
 *
 * @Bean 和 @Configuration 注解代替xml 生成一个组件
 */
public class BeanTest {

    public static void main(String[] args) {

//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
//
//        Person person = (Person) classPathXmlApplicationContext.getBean("person");
//        System.out.println(person);


        // 注解的方式 实现Config 和 bean的注入
       ApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(PersonConfig.class); // 导入配置类
        Person personOfConfig = (Person) configApplicationContext.getBean("personOfConfig");
        System.out.println(personOfConfig);

    }

    @Test
    public void testScopeAndLazy() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        System.out.println("spring环境初始化完成");

        Book book = context.getBean("book", Book.class);
        Book book1 = context.getBean("book", Book.class);

        System.out.println(book == book1);
    }
}
