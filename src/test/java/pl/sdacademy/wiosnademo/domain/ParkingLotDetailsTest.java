package pl.sdacademy.wiosnademo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParkingLotDetailsTest {

  @Test
  void testa() {
    ParkingLotDetails parkingLotDetails = new ParkingLotDetails();
    parkingLotDetails.setMobileNumber("123-123-321");
    assertTrue(parkingLotDetails.isMobileNumberValid());
  }

  @Test
  void testb() {
    ParkingLotDetails parkingLotDetails = new ParkingLotDetails();
    parkingLotDetails.setMobileNumber("123-123-3221");
    assertFalse(parkingLotDetails.isMobileNumberValid());
  }

  @Test
  void testc() {
    ParkingLotDetails parkingLotDetails = new ParkingLotDetails();
    parkingLotDetails.setMobileNumber("123123221");
    assertFalse(parkingLotDetails.isMobileNumberValid());
  }
}