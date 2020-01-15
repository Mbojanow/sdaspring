package pl.sdacademy.wiosnademo.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "pl.sdacademy")
//@EnableConfigurationProperties(UsersConfiguration.class)
@Configuration
public class UsersConfiguration {

    private List<String> users;
    private List<String> passwords;
}
