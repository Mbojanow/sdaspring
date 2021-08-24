package com.example.validation_example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thing {

    @Id
    //@GeneratedValue
    private Long id;

//    @NotNull
//    @NotEmpty
//    @Past

    @Length(min = 3, message = "name needs to have at least 3 characters")
    private String name;
}
