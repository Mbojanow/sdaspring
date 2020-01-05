package pl.sdacademy.wiosnademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Null(message = "id has not to be provided")
    private Long id;

    @NotNull(message = "consignor is a mandatory field")
    @Length(min = 3, message = "consignor length has to be at least 3")
    @JsonProperty("consignor")
    private String from;

    @NotNull(message = "recipent is a mandatory field")
    @Length(min = 3, message = "recipent length has to be at least 3")
    @JsonProperty("recipent")
    private String to;

    @NotNull(message = "message value has to be provided")
    @Length(min = 1, message = "message value cannot be empty")
    private String value;

    private LocalDateTime sendDate;

    @JsonIgnore
    public boolean isInFuture() {
        return sendDate.isAfter(LocalDateTime.now());
    }

}
