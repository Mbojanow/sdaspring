package pl.sdacademy.wiosnademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String email;
    private String password;
    private Status status;
}
