package app.mcgg.rpn.util;

import app.mcgg.rpn.operator.Operator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *  Enable ApplicationContextAware to get all Beans managed by Spring framework,
 *  this is used by BeanCache bean
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ApplicationContextProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Operator getBeanByName(String name) {
        return (Operator) this.applicationContext.getBean(name);
    }
}
