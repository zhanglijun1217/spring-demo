package value;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zlj on 2019/8/11.
 */
@Data
@ToString
public class ValueBean {

    @Value(value = "${valueBean.name}")
    private String name;
    @Value(value = "${valueBean.age}")
    private Integer age;
}
