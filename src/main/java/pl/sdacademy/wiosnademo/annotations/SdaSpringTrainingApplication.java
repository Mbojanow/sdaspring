package pl.sdacademy.wiosnademo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;

@SpringBootApplication()
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SdaSpringTrainingApplication {

  @AliasFor(annotation = SpringBootApplication.class, attribute = "exclude") // mapowanie z exclude do excludeClasses
  Class<?>[] excludeClasses() default {};

  @AliasFor(annotation = SpringBootApplication.class) // mapowanie po nazwie 1 do 1
  String[] excludeName() default {};

  @AliasFor(annotation = SpringBootApplication.class, attribute = "scanBasePackages")
  String[] scanPackagesForComponents() default {};

  @AliasFor(annotation = SpringBootApplication.class)
  Class<?>[] scanBasePackageClasses() default {};

  @AliasFor(annotation = SpringBootApplication.class)
  boolean proxyBeanMethods() default false;
}
