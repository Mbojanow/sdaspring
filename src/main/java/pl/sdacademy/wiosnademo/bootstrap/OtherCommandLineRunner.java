package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.stereotype.Component;
import pl.sdacademy.OtherComponent;

@Component
public class OtherCommandLineRunner {
    private final HelloLog helloLog;
    private final OtherComponent otherComponent;

    public OtherCommandLineRunner(/*@Qualifier("helloLogger")*/ final HelloLog helloLog, final OtherComponent otherComponent) {
        this.helloLog = helloLog;
        this.otherComponent = otherComponent;
    }
}
