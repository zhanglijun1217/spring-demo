package conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class MacCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取当前运行的环境信息
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if (!StringUtils.isEmpty(property) && property.contains("Mac")) {
            return true;
        }
        return false;
    }
}
