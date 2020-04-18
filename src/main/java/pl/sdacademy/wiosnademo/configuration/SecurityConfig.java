package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
        .withUser("user1").password("{noop}admin").roles("ADMIN")
        .and()
        .withUser("user2").password("{noop}admin").roles("ADMIN")
        .and()
        .withUser("user3").password("{noop}admin").roles("ADMIN");
  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return NoOpPasswordEncoder.getInstance();
//  }
}
