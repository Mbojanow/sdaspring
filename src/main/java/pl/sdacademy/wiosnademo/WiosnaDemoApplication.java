package pl.sdacademy.wiosnademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pl.sdacademy")
public class WiosnaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiosnaDemoApplication.class, args);
	}

}
