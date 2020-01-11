package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableGlobalMethodSecurity
//        (jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService().passwordEncoder()
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}pwd123")
//                .roles("USER");
//    }
//}
}
