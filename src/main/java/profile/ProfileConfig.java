package profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * Created by zlj on 2019/10/4.
 * 不同环境的切换
 * 这里以数据源来演示。不同的环境连本地不一样的库
 */
@PropertySource(value = "classpath:/db.properties")
@Configuration
@ComponentScan(basePackages = "profile")
public class ProfileConfig {

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driverClass;

    @Value("${db.jdbc.url}")
    private  String jdbcUrl;


    @Bean
    @SneakyThrows
    @Profile("dev")
    public DataSource dataSourceDev() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl + "taotao");

        return dataSource;
    }

    @Bean
    @SneakyThrows
    @Profile("test")
    public DataSource dataSourceTest() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl + "test_logistics");

        return dataSource;
    }

    @Bean
    @SneakyThrows
    @Profile("prod")

    public DataSource dataSourceProd() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl + "world");

        return dataSource;
    }
}
