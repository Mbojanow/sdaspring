package pl.sdacademy.wiosnademo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.annotation.AliasFor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
public @interface JavaGdy5Application {

  @AliasFor(value = "proxyBeanMethods", annotation = SpringBootApplication.class)
  boolean proxyMethodBeans() default true;

  @AliasFor(value = "exclude", annotation = SpringBootApplication.class)
  Class<?>[] excludeClasses() default { SecurityAutoConfiguration.class };
}
