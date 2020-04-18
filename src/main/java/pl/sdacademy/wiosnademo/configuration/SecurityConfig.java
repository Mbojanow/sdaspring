package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /* WYKORZYSTANIE @Value
  //@Value("${pl.sdacademy.user.name:user1}")
  private String username;

  //@Value("${pl.sdacademy.user.password}")
  private String password;

  public SecurityConfig(@Value("${pl.sdacademy.user.name:user1}") final String username,
                        @Value("${pl.sdacademy.user.password}") final String password) {
    this.username = username;
    this.password = password;
  }
  */

  private final UsersConfiguration usersConfiguration;

  public SecurityConfig(final UsersConfiguration usersConfiguration) {
    this.usersConfiguration = usersConfiguration;
  }


  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // ? - 1 znak
        // * - 0-n znaków, do /
        // ** - 0-n znaków, do końca ścieżki
        .antMatchers(HttpMethod.GET, "/**").permitAll()
        .antMatchers(HttpMethod.POST, "/**").authenticated()
        .antMatchers(HttpMethod.PUT, "/**").authenticated()
        .antMatchers(HttpMethod.PATCH, "/**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/**").authenticated()
        .anyRequest().permitAll()
//        .antMatchers("/api/dummy").authenticated()
//        .antMatchers("/api/parking-lots**").permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser(usersConfiguration.getUserA()).password(usersConfiguration.getPasswordA()).roles("ADMIN")
        .and()
        .withUser(usersConfiguration.getUserB()).password(usersConfiguration.getPasswordB()).roles("ADMIN")
        .and()
        .withUser(usersConfiguration.getUserC()).password(usersConfiguration.getPasswordC()).roles("ADMIN");
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
