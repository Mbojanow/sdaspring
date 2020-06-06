package pl.sdacademy.wiosnademo.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
//@DependsOn("httpRequestHandlerAdapter")
public class BeanCounter implements BeanPostProcessor {

  public static int beanCount = 0;

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    beanCount++;
    return bean;
  }
}
