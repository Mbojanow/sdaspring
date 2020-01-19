package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
}

//    http.authorizeRequests()
//        .antMatchers("/api/dummy").authenticated()
//        .antMatchers("/api/parking-lots**").permitAll()
//        .and()
//        .httpBasic()
//        .and()
//        .csrf().disable();
