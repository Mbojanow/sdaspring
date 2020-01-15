package pl.sdacademy.wiosnademo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity
        (securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersConfiguration usersConfiguration;

    public SecurityConfig(final UsersConfiguration usersConfiguration) {
        this.usersConfiguration = usersConfiguration;
    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/**").hasRole("ADD_OR_MODIFY")
//                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADD_OR_MODIFY")
//                .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADD_OR_MODIFY")
//                .antMatchers(HttpMethod.DELETE, "/**").hasRole("REMOVE")
//                .antMatchers(HttpMethod.GET, "/**").hasRole("READ")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user1").password("admin").roles("READ")
                .and()
                .withUser("user2").password("admin").roles("READ", "ADD_OR_MODIFY")
                .and()
                .withUser("user3").password("admin").roles("READ", "ADD_OR_MODIFY", "REMOVE");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

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
