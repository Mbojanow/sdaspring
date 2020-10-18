package pl.sdacademy.wiosnademo.domain;

/*
• pole name – wymagane o długości przynajmniej 3
• pole description – opis produktu, o długości przynajmniej 5
• pole producer – wymagane, niepusty
• Pole cena – dodatnia, co najwyżej 2 miejsca po przecinku
 */

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Length(min = 3, max = 255)
  @Column(name = "name")
  private String name;

  @NotNull
  @Length(min = 5, max = 4095)
  @Column(name = "description")
  private String description;

  @NotEmpty
  @Column(name = "producer")
  private String producer;

  @NotNull
  @Column(name = "price")
  private Double price;

  @OneToMany
  @JoinColumn(name = "product_id")
  private List<Stock> stocks;
}
