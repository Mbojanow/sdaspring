package pl.sdacademy.wiosnademo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity(name = "imgs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Lob
  private byte[] image;
}
