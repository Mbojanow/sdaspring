package com.example.validation_example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    @Email
    private String email;

    @Length(min = 3, message = "dlugosc musi byc przynajmniej 3")
    private String usernameX;
}
