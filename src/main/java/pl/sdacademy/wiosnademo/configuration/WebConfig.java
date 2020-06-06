package pl.sdacademy.wiosnademo.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import pl.sdacademy.wiosnademo.converters.HtmlOutputHttpMessageConverter;
import pl.sdacademy.wiosnademo.interceptors.BlockingHandlerInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    converters.add(new HtmlOutputHttpMessageConverter());
    converters.forEach(converter -> {
      System.out.println(converter.getSupportedMediaTypes());
      System.out.println(converter.getClass().getSimpleName());
    });
  }

  @Override
  public void configurePathMatch(final PathMatchConfigurer configurer) {
    final UrlPathHelper urlPathHelper = new UrlPathHelper();
    urlPathHelper.setRemoveSemicolonContent(false);
    configurer.setUrlPathHelper(urlPathHelper);
  }

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(new BlockingHandlerInterceptor()).order(1).addPathPatterns("/api/v1/courses/**");
  }
}
