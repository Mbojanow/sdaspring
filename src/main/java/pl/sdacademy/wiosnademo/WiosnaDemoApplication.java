package pl.sdacademy.wiosnademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import pl.sdacademy.UncaughtBean;

@SpringBootApplication(/*exclude = WebClientAutoConfiguration.class*/) //wylaczanie autokonfiguracji
@ComponentScan(basePackages = "pl.sdacademy")
public class WiosnaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiosnaDemoApplication.class, args);
	}

	@Autowired
	private UncaughtBean uncaughtBean;

}
