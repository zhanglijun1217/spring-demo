package value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zlj on 2019/8/11.
 */
@Configuration
@PropertySource(value = "application.properties", encoding = "utf-8")
public class ValuePropertiesConfig {

    @Bean
    public ValueBean valueBean() {
        return new ValueBean();
    }
}
