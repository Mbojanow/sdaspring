package pl.sdacademy.wiosnademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;
import pl.sdacademy.wiosnademo.services.CustomUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true) // włącza możliwość korzystania z @Secured
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService customUserDetailsService;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()// *, **, ?
        .antMatchers("/h2/**").permitAll()
        // role to authority z przedroskiego ROLE_
        //.antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN") // wymagam authority ROLE_ADMIN
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

  @Override
  protected UserDetailsService userDetailsService() {
    return customUserDetailsService;
  }
}
