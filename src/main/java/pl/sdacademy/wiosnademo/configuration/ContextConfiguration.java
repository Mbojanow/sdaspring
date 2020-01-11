package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@COmponentScan - wiele w jednej aplikacji - wszystkie beany w paczce pl.sdacademy trafiaja
// do konteneru IoC
@ComponentScan(basePackages = "pl.sdacademy")
@Configuration
public class ContextConfiguration {
}
