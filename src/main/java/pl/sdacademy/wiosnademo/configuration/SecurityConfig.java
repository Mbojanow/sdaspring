package pl.sdacademy.wiosnademo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableGlobalMethodSecurity
//        (jsr250Enabled = true, securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersConfiguration usersConfiguration;

    @Value("${pl.sdacademy.user}")
    private String username;

    public SecurityConfig(final UsersConfiguration usersConfiguration) {
        this.usersConfiguration = usersConfiguration;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/parking-lots**", "/api/dummy").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/v1/parking-lots**", "/api/dummy").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/v1/parking-lots**", "/api/dummy").authenticated()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user1").password(passwordEncoder().encode("admin")).authorities("USER1")
                .and()
                .withUser("user2").password(passwordEncoder().encode("admin")).roles("USER1");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
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
