package 自动装配;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by zlj on 2019/9/1.
 */
@Repository
@Data
//@Primary
public class TestDao {

    private int label = 1;
}
