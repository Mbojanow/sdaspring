package pl.sdacademy.wiosnademo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DemoTest {

    @Test
    public void test() {
        System.out.println(LocalDateTime.now().plusDays(1).toEpochSecond(ZoneOffset.UTC));
    }
}
