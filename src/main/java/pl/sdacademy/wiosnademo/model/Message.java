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

    @Null
    private Long id;

    @NotNull
    @Length(min = 3)
    @JsonProperty("consignor")
    private String from;

    @NotNull
    @Length(min = 3)
    @JsonProperty("recipent")
    private String to;

    @NotNull
    @Length(min = 1)
    private String value;

    private LocalDateTime sendDate;

    @JsonIgnore
    public boolean isInFuture() {
        return sendDate.isAfter(LocalDateTime.now());
    }

}
