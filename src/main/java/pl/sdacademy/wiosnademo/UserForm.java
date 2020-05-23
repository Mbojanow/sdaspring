package pl.sdacademy.wiosnademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @Length(min = 3)
    private String username;
    private String password;
    private String email;
}
