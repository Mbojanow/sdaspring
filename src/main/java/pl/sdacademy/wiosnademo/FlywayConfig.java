package pl.sdacademy.wiosnademo;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig implements FlywayConfigurationCustomizer {

  @Override
  public void customize(final FluentConfiguration configuration) {
    configuration.locations("classpath:pl.sdacademy.wiosnademo.migrations", "classpath:db.migration");
  }
}
