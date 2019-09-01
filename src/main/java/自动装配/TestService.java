package 自动装配;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by zlj on 2019/9/1.
 */
@Service
@ToString
public class TestService {

//    @Autowired
//    private TestDao testDao2; // 这里修改下beanName， 当一个类型有多实例bean的时候，默认优先匹配id

//    @Autowired
//    @Qualifier(value = "testDao2") // Qualifier注解指定多实例下的bean
//    private TestDao testDao;

//    @Autowired
//    private TestDao testDao; // 在testDao2bean初始化的地方去注释了 @Primary


//    @Resource
//    private TestDao testDao;

    // 通过javax.inject来注解bean  inject支持@Qualifier注解
    @Inject
    @Qualifier("testDao2")
    private TestDao testDao;
}
