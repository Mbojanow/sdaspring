package pl.sdacademy.wiosnademo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "stocks")
public class Stock {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Min(0)
  @Column(name = "quantity")
  private Long quantity;

  @JsonIgnore
  @ManyToOne
  private Shop shop;

  @JsonIgnore
  @ManyToOne
  private Product product;
}


