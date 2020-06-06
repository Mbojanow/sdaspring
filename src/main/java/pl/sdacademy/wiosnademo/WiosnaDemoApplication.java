package pl.sdacademy.wiosnademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import pl.sdacademy.wiosnademo.annotations.SdaSpringTrainingApplication;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SdaSpringTrainingApplication(excludeClasses = DataSourceAutoConfiguration.class, proxyBeanMethods = true)
public class WiosnaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiosnaDemoApplication.class, args);
	}

}
