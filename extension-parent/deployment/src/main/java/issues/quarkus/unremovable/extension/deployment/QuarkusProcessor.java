package issues.quarkus.unremovable.extension.deployment;

import io.quarkus.arc.deployment.AutoAddScopeBuildItem;
import io.quarkus.arc.deployment.UnremovableBeanBuildItem;
import io.quarkus.arc.processor.BeanInfo;
import io.quarkus.arc.processor.BuiltinScope;
import io.quarkus.deployment.annotations.BuildStep;
import org.jboss.jandex.MethodInfo;

import java.util.function.Predicate;

class QuarkusProcessor {

    private static final String FEATURE = "testing-unremovable";

    @BuildStep
    AutoAddScopeBuildItem handlersToBeans() {
        return AutoAddScopeBuildItem.builder()
                .anyMethodMatches(isBeanMaking)
                .unremovable()
                .defaultScope(BuiltinScope.APPLICATION)
                .build();
    }

    @BuildStep
    UnremovableBeanBuildItem makeHandlersUnremovable() {
        return new UnremovableBeanBuildItem(new HandlersExclusion());
    }

    public static class HandlersExclusion implements Predicate<BeanInfo> {
        @Override
        public boolean test(BeanInfo bean) {
            return bean.getImplClazz().methods().stream().anyMatch(isBeanMaking);
        }
    }

    static Predicate<MethodInfo> isBeanMaking = method -> method.name().startsWith("makeMeBean");

}
