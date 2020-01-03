package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnApplicationStartupRunner implements CommandLineRunner {

    private final HelloLog helloLog;

    public OnApplicationStartupRunner(/*@Qualifier("helloLogger")*/ final HelloLog helloLog) {
        this.helloLog = helloLog;
    }

    @Override
    public void run(final String... args) {
        helloLog.logHello();
    }
}
