package test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import 自动装配.*;

/**
 * Created by zlj on 2019/9/1.
 */
public class Test自动装配 {

    @Test
    public void testAutowired() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutowiredConfig.class);

        // 拿到service bean
        TestService bean = annotationConfigApplicationContext.getBean(TestService.class);
        // 打印service把 dao变量地址打印出
        System.out.println(bean);

        // 拿到组件dao 和上面输出dao地址一致
//        System.out.println(annotationConfigApplicationContext.getBean(TestDao.class));


        /**
         * 在非属性位置上用autowired去实现自动装配
         */
        OuterBean outerBean = annotationConfigApplicationContext.getBean(OuterBean.class);
        System.out.println(outerBean);

        // 获取innerbean
        InnerBean in = annotationConfigApplicationContext.getBean(InnerBean.class);
        System.out.println(in);


    }

}
