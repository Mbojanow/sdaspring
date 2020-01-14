package pl.sdacademy.wiosnademo.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SecurityConfigTest {

    @Test
    public void hi() {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

}