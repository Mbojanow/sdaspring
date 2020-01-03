package pl.sdacademy.wiosnademo.bootstrap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloLogger2 implements HelloLog {

    @Override
    public void logHello() {
        log.info("Hello from logger2");
    }
}
