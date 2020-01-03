package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloLogger implements HelloLog {

    @Override
    public void logHello() {
        log.info("Hello from hello logger");
    }
}
