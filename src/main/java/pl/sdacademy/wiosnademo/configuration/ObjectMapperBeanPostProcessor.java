package pl.sdacademy.wiosnademo.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Profile("p3")
@Component
public class ObjectMapperBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {

    if (beanName.equals("jacksonObjectMapper")) {
      final ObjectMapper objectMapper = (ObjectMapper)bean;
      objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    return bean;
  }
}
