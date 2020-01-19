package pl.sdacademy.wiosnademo.repositories;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.wiosnademo.domain.Reservation;

public class DateTest {

  @Test
  public void test() throws JsonProcessingException {
    final long timestamp = new Date().getTime() + 100000;
    final Reservation reservation = new Reservation(null, "Andrzej", new Date(timestamp),
        new Date(timestamp + 1000), "GDA 1234", null);
    final ObjectMapper objectMapper = new ObjectMapper();

    final String output = objectMapper.writeValueAsString(reservation);
    System.out.println(output);
  }
}
