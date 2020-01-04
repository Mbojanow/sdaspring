package pl.sdacademy.wiosnademo.bootstrap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.sdacademy.OtherComponent;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(SCOPE_SESSION)
public class OtherCommandLineRunner {
    private final HelloLog helloLog;
    private final OtherComponent otherComponent;

    public OtherCommandLineRunner(/*@Qualifier("helloLogger")*/ final HelloLog helloLog, final OtherComponent otherComponent) {
        this.helloLog = helloLog;
        this.otherComponent = otherComponent;
    }
}
