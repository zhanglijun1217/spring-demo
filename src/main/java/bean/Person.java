package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by zlj on 2019/6/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private String name;
    private Integer age;
}
