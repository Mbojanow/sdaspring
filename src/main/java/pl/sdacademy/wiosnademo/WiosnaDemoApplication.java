package pl.sdacademy.wiosnademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.sdacademy.SomeComponent;

@SpringBootApplication
public class WiosnaDemoApplication {

	@Autowired
	private SomeComponent someComponent;

	public static void main(String[] args) {
		SpringApplication.run(WiosnaDemoApplication.class, args);
	}

}
