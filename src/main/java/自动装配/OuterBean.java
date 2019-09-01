package 自动装配;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zlj on 2019/9/1.
 */
@Component  // 默认component是调用无参数构造函数进行初始化的
@ToString
public class OuterBean {


    private InnerBean innerBean;

    // 在构造函数中进行自动注入
    @Autowired
    public OuterBean(InnerBean innerBean) {
        System.out.println("正在构造了outerBean");
        this.innerBean = innerBean;
    }

    public InnerBean getInnerBean() {
        return innerBean;
    }

    // 可以在set方法上加上autowired自动注入
//    @Autowired
    public void setInnerBean(InnerBean innerBean) {
        this.innerBean = innerBean;
    }
}
