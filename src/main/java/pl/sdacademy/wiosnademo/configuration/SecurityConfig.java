package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//  @Value("${sda.security.username:andrzej}") // po dwukropku - wartość domyślna
//  private String username;
//  @Value("${sda.security.password}")
//  private String password;

  private final UsersConfig usersConfig;
  private final UsersConfigOnMap usersConfigOnMap;

  public SecurityConfig(final UsersConfig usersConfig, final UsersConfigOnMap usersConfigOnMap) {
    this.usersConfig = usersConfig;
    this.usersConfigOnMap = usersConfigOnMap;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // ** - n znaków do końca URLa
//        .antMatchers("/api/dummy**").authenticated()
//        .antMatchers("/api/parking-lots**").permitAll()
        .antMatchers(HttpMethod.POST, "/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/**").authenticated()
        .antMatchers(HttpMethod.GET, "/**").permitAll()
        .anyRequest().permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    // stworzy użytkowników wykorzystując usersConfigOnMap, a nie usersConfig
    final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> builder = auth
        .inMemoryAuthentication();
    int index = 0;
    for (final var entry : usersConfigOnMap.getCredentials().entrySet()) {
      builder.withUser(entry.getKey()).password("{noop}" + entry.getValue()).roles("ADMIN");
      if (++index != usersConfigOnMap.getCredentials().size()) {
        builder.and();
      }
    }
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
