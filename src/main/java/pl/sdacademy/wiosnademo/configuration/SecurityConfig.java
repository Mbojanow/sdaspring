package pl.sdacademy.wiosnademo.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@EnableWebSecurity - domyślnie włączone, nie potrzeba deklarować w przypadku korzustania ze startera security
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

                                            // idx usera % 3 == 0 -> READ
                                            // idx usera % 3 == 1 -> ADD_OR_MODIFY
                                            // idx usera % 3 == 2 -> REMOVE
  private static List<String> ROLES = List.of("READ", "ADD_OR_MODIFY", "REMOVE");

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
  private final UserConfigurationOnLists userConfigurationOnLists;

  public SecurityConfig(final UsersConfiguration usersConfiguration,
                        final UserConfigurationOnLists userConfigurationOnLists) {
    this.usersConfiguration = usersConfiguration;
    this.userConfigurationOnLists = userConfigurationOnLists;
  }


  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // ? - 1 znak
        // * - 0-n znaków, do /
        // ** - 0-n znaków, do końca ścieżki
                                                       //hasAuthority("ROLE_READ")

        /*
        ANT MATCHERY BEZ @SECURED
        .antMatchers(HttpMethod.GET, "/**").hasAuthority("ROLE_READ")
        .antMatchers(HttpMethod.POST, "/**").hasRole("ADD_OR_MODIFY")
        .antMatchers(HttpMethod.PUT, "/**").hasRole("ADD_OR_MODIFY")
        .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADD_OR_MODIFY")
        .antMatchers(HttpMethod.DELETE, "/**").hasRole("REMOVE")
         */
        .antMatchers("/**").authenticated()
        .anyRequest().permitAll()
//        .antMatchers("/api/dummy").authenticated()
//        .antMatchers("/api/parking-lots**").permitAll()
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
        // pozwala na działanie konsoli h2 w przeglądarce -dodawać do profilu developerskiego
        .headers().frameOptions().disable();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    //userConfigurationOnLists - wszystkich uzytkownikow i hasła z tej listy
    final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth
        .inMemoryAuthentication();
    for (int idx = 0; idx < userConfigurationOnLists.getUsernames().size(); idx++) {
      configurer.withUser(userConfigurationOnLists.getUsernames().get(idx))
          .password(userConfigurationOnLists.getPasswords().get(idx))
          .roles(ROLES.get(idx % ROLES.size())); // authority ROLE_ADMIN
      if (userConfigurationOnLists.getUsernames().size() != (idx + 1)) {
        configurer.and();
      }
    }

// 5 % 2 -> 1
// 10 % 3 -> 1

//    auth.inMemoryAuthentication()
//        .withUser(usersConfiguration.getUserA()).password(usersConfiguration.getPasswordA()).roles("ADMIN")
//        .and()
//        .withUser(usersConfiguration.getUserB()).password(usersConfiguration.getPasswordB()).roles("ADMIN")
//        .and()
//        .withUser(usersConfiguration.getUserC()).password(usersConfiguration.getPasswordC()).roles("ADMIN");
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
