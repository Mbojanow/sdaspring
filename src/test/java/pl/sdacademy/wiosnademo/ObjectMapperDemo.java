package pl.sdacademy.wiosnademo;

import java.lang.reflect.Member;
import java.util.List;

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
        .someStrings(List.of("str1", "str2", "str3"))
        .build();

    final String output = objectMapper.writeValueAsString(someObject);
    System.out.println(output);
  }

  @Test
  void testB() throws JsonProcessingException {
    List<Message> messages = List.of(new Message(), new Message());

    final String output = objectMapper.writeValueAsString(messages);
    System.out.println(output);
  }
}
