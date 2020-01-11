package pl.sdacademy.wiosnademo;

import java.lang.reflect.Member;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sdacademy.wiosnademo.domain.Message;

public class ObjectMapperDemo {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void testA() throws JsonProcessingException {
    final SomeObject someObject = SomeObject.builder()
        .id(1L)
        .someValue("hello")
        .build();

    final String output = objectMapper.writeValueAsString(someObject);
    System.out.println(output);
  }

}
