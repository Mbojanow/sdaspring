package pl.sdacademy.wiosnademo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Messages {
  @JsonProperty("messages")
  private List<Message> messages;
}
