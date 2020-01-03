package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnApplicationStartupRunner implements CommandLineRunner {

    private final HelloLogger helloLogger;
    private final HelloLogger2 helloLogger2;

    public OnApplicationStartupRunner(final HelloLogger helloLogger, final HelloLogger2 helloLogger2) {
        this.helloLogger = helloLogger;
        this.helloLogger2 = helloLogger2;
    }

    @Override
    public void run(final String... args) throws Exception {
        helloLogger.logHello();
        helloLogger2.doSomeLog();
    }
}
