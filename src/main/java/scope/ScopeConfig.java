package scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Created by zlj on 2019/7/16.
 */
@Configuration
public class ScopeConfig {


    @Bean("book")
    @Lazy // 懒加载单例bean对象
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Book getBook() {
        System.out.println("book对象正在初始化");
        return new Book("book1", 122);
    }
}
