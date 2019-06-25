package test;

import config.ComponentScanConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by zlj on 2019/6/25.
 */
public class TestComponentScanAnno {

    @Test
    public void testComponentScanAnno() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames())
                .forEach(System.out::println);
    }
}
