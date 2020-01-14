package pl.sdacademy.wiosnademo.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "pl.sdacademy")
//@EnableConfigurationProperties(UsersConfiguration.class)
@Configuration
public class UsersConfiguration {

    private String userA;
    private String passwordA;
}
