package app.shopping.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value = "applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextHolder.CONTEXT_PROV.context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) {
        ApplicationContextHolder.CONTEXT_PROV.setContext(ac);
    }

    private static class ApplicationContextHolder {
        private static final InnerContextResource CONTEXT_PROV = new InnerContextResource();
    }

    private static final class InnerContextResource {
        private ApplicationContext context;

        private void setContext(ApplicationContext context) {
            this.context = context;
        }
    }

}