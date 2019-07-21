package conditional;

import bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

    @Bean("bill")
    @Conditional({WindowsCondition.class})// @Conditional#value是一个Condition接口数组，实现其中的match方法
    public Person getWindows() {
        return new Person("bill", 22);
    }

    @Bean("jobs")
    @Conditional({MacCondition.class})
    public Person getMac() {
        return new
                Person("jobs", 33);
    }
}
