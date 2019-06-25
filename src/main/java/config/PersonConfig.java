package config;

import bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zlj on 2019/6/25.
 */
@Configuration
public class PersonConfig {

    @Bean(name = "personOfConfig") // name指定bean的名称
    public Person person() {
        return new Person("lisi", 19);
    }
}
