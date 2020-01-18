package pl.sdacademy.wiosnademo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.sdacademy.wiosnademo.domain.ParkingLot;
import pl.sdacademy.wiosnademo.exceptions.ParkingLotGenericException;
import pl.sdacademy.wiosnademo.repositories.ParkingLotRepository;

//@RunWith(MockitoJUnitExtension.class - wersja JUnit4
@ExtendWith(MockitoExtension.class)
class ParkingLotServiceTest {

  @Mock
  private ParkingLotRepository parkingLotRepository;

  @Mock
  private ParkingLotCommonValidator parkingLotCommonValidator;

  @Mock // zastępuje Mockito.mock(ParkingLotPartialUpdateValidator.class)
  private ParkingLotPartialUpdateValidator parkingLotPartialUpdateValidator;

  @InjectMocks // zastępuje new ParkingLotService(parkingLotRepository,
  // parkingLotCommonValidator, parkingLotPartialUpdateValidator)
  private ParkingLotService parkingLotService;

  @Test
  void shouldCreateNewParkingLot() {
    final String name = "dasihoeqw";
    final ParkingLot parkingLotToCreate = new ParkingLot(null, name, "asd", 5L, null);
    final ParkingLot createdParkingLot = new ParkingLot();
    createdParkingLot.setName(name);
    //import static org.mockito.Mockito.when;
    when(parkingLotRepository.findByName(name)).thenReturn(Optional.empty());
    when(parkingLotRepository.create(parkingLotToCreate)).thenReturn(createdParkingLot);

    final ParkingLot actualParkingLot = parkingLotService.create(parkingLotToCreate);

    assertThat(actualParkingLot).isEqualTo(createdParkingLot);
  }

  @Test
  void shouldThrowWhenParkingWithNameAlreadyExists() {
    final String name = "dashiuqewiuhd";
    final ParkingLot parkingLotToCreate = new ParkingLot();
    parkingLotToCreate.setName(name);
    when(parkingLotRepository.findByName(name)).thenReturn(Optional.of(parkingLotToCreate));

    assertThatExceptionOfType(ParkingLotGenericException.class)
        .isThrownBy(() -> parkingLotService.create(parkingLotToCreate))
        .withMessage("Parking lot with name " + name + " already exists");
  }
}