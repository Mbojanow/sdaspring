package pl.sdacademy.wiosnademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()// *, **, ?
        .antMatchers("/h2/**").permitAll()
        .and()
        .formLogin()
        .and()
        .httpBasic()
        .and()
        .logout()
        .and()
        .csrf().ignoringAntMatchers("/h2/**")
        .and()
        .headers().frameOptions().disable();
  }
}
