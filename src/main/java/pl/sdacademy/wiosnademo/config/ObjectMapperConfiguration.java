package pl.sdacademy.wiosnademo.config;

//konfiguracja istniejacego beana ObjectMapper - obiekt do konwersji json <-> java

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Profile("dev")
@Component
public class ObjectMapperConfiguration implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    if (beanName.equals("jacksonObjectMapper")) {
      final ObjectMapper objectMapper = (ObjectMapper)bean;
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
      return objectMapper;
    }
    return bean;
  }
}
