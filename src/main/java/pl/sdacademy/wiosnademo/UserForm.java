package pl.sdacademy.wiosnademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    private String username;
    private String password;
    private String email;
}
