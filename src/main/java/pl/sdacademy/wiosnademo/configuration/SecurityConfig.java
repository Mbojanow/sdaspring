package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true) // to pozwala na korzystanie z adnotacji @Secured w aplikacji
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
//        .antMatchers(HttpMethod.POST, "/**").hasRole("ADD_OR_MODIFY") //. hasAuthority("ROLE_READ")
//        .antMatchers(HttpMethod.PUT, "/**").hasRole("ADD_OR_MODIFY")
//        .antMatchers(HttpMethod.DELETE, "/**").hasRole("REMOVE")
//        .antMatchers(HttpMethod.GET, "/**").hasRole("READ")
        .antMatchers("/**").authenticated() // dwie ** -> do końca stringa
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
      builder.withUser(entry.getKey()).password("{noop}" + entry.getValue()).roles(calculateRole(index));
      if (++index != usersConfigOnMap.getCredentials().size()) {
        builder.and();
      }
    }
  }

  private String calculateRole(final int index) {
    if (index % 3 == 0) {
      return "READ";
    }
    if (index % 3 == 1) {
      return "ADD_OR_MODIFY";
    }
    return "REMOVE";
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
