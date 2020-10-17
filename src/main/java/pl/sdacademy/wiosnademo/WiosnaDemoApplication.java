package pl.sdacademy.wiosnademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.sdacademy.wiosnademo.annotations.JavaGdy5Application;

@JavaGdy5Application
public class WiosnaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiosnaDemoApplication.class, args);
	}

}
