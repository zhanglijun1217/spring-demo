package test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import value.ValueBean;
import value.ValuePropertiesConfig;

/**
 * Created by zlj on 2019/8/11.
 */
public class ValueBeanTest {

    @Test
    public void testValue() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ValuePropertiesConfig.class);

        ValueBean bean = context.getBean(ValueBean.class);

        System.out.println(bean);
    }

    @Test
    public void testProperty() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");

        ValueBean bean = classPathXmlApplicationContext.getBean(ValueBean.class);

        System.out.println(bean);
    }
}
