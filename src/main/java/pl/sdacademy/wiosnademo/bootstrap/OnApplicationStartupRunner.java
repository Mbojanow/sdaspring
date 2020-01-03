package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnApplicationStartupRunner implements CommandLineRunner {

    @Override
    public void run(final String... args) throws Exception {
        log.info("Hello from command line runner");
    }
}
