package issues.quarkus.unremovable.extension.deployment;

import io.quarkus.arc.deployment.AutoAddScopeBuildItem;
import io.quarkus.arc.processor.BuiltinScope;
import io.quarkus.deployment.annotations.BuildStep;

class QuarkusProcessor {

    private static final String FEATURE = "testing-unremovable";

    @BuildStep
    AutoAddScopeBuildItem handlersToBeans() {
        return AutoAddScopeBuildItem.builder()
                .anyMethodMatches(method -> {
                    return method.name().startsWith("makeMeBean");
                })
                .unremovable()
                .defaultScope(BuiltinScope.APPLICATION)
                .build();
    }


}
