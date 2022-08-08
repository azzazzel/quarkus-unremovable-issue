package issues.quarkus.unremovable.sample;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;

@QuarkusMain
public class HelloApplication implements QuarkusApplication {

    @Inject
    BeanManager beanManager;

    @Override
    public int run(String... args) throws IOException {

        Set beans = beanManager.getBeans(NotBean.class);
        beans.stream().forEach(System.out::println);

        return 0;
    }
}
