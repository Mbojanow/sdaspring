package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sdacademy.OtherComponent;

@Component
@Slf4j
public class OnApplicationStartupRunner implements CommandLineRunner {

    private final HelloLog helloLog;
    private final OtherComponent otherComponent;

    public OnApplicationStartupRunner(/*@Qualifier("helloLogger")*/ final HelloLog helloLog, final OtherComponent otherComponent) {
        this.helloLog = helloLog;
        this.otherComponent = otherComponent;
    }

    @Override
    public void run(final String... args) {
        helloLog.logHello();
    }
}
