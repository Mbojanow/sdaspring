package pl.sdacademy.wiosnademo.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Profile("p2") //bean trafi do kontekstu tylko gdy wlaczony bedzie profil p2
@Component
public class CustomObjectMapperConfiguration implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    if (beanName.equals("jacksonObjectMapper")) {
      final ObjectMapper objectMapper = (ObjectMapper)bean;
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
      return objectMapper;
    }
    return bean;
  }
}
