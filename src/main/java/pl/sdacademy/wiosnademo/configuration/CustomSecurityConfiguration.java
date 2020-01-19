package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UsersConfiguration usersConfiguration;

  public CustomSecurityConfiguration(final UsersConfiguration usersConfiguration) {
    this.usersConfiguration = usersConfiguration;
  }

//  @Value("${sda.user.name}")
//  private String username;
//
//  @Value("${sda.user.password}")
//  private String password;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/dummy", "/api/parking-lots/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/dummy", "/api/parking-lots**").authenticated()
        .antMatchers(HttpMethod.PUT, "/api/dummy", "/api/parking-lots**").authenticated()
        .antMatchers(HttpMethod.PATCH, "/api/dummy", "/api/parking-lots**").authenticated()
        .antMatchers(HttpMethod.DELETE, "/api/dummy", "/api/parking-lots**").authenticated()
        .anyRequest().permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
            .headers().frameOptions().disable();
        //.anyRequest().permitAll();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> builder =
        auth.inMemoryAuthentication();
    for (int idx = 0; idx < usersConfiguration.getUsernames().size(); idx++) {
      builder.withUser(usersConfiguration.getUsernames().get(idx))
          .password("{noop}" + usersConfiguration.getPasswords().get(idx))
          .roles("ADMIN")
          .and();
    }
//        .withUser(usersConfiguration.getUserA())
//        .password("{noop}" + usersConfiguration.getPasswordA()).
//        roles("ADMIN")
//        .and()
//        .withUser("user2").password("{noop}admin").roles("ADMIN")
//        .and()
//        .withUser("user3").password("{noop}admin").roles("ADMIN");
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}

//    http.authorizeRequests()
//        .antMatchers("/api/dummy").authenticated()
//        .antMatchers("/api/parking-lots**").permitAll()
//        .and()
//        .httpBasic()
//        .and()
//        .csrf().disable();
