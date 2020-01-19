package pl.sdacademy.wiosnademo.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final Map<Integer, List<String>> USER_INDEX_TO_ROLES = Map.of(0, List.of("READ"),
      1, List.of("READ", "ADD_OR_MODIFY"),
      2, List.of("READ", "ADD_OR_MODIFY", "REMOVE"));

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
        .antMatchers(HttpMethod.GET, "/api/parking-lots/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/parking-lots**").hasAuthority("ROLE_READ")
        .antMatchers(HttpMethod.PUT, "/api/parking-lots**").hasRole("ADD_OR_MODIFY")
        .antMatchers(HttpMethod.PATCH, "/api/parking-lots**").hasRole("ADD_OR_MODIFY")
        .antMatchers(HttpMethod.DELETE, "/api/parking-lots**").hasRole("REMOVE")
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
          // hak - 0 user - READ, 1 user - ADD_OF_MODIFY, 2 user - REMOVE, 3 user - boom
          .roles(USER_INDEX_TO_ROLES.get(idx).get(idx))
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
