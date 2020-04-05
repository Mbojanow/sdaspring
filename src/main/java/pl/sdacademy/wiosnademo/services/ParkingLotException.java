package pl.sdacademy.wiosnademo.services;

import lombok.Getter;

@Getter
public class ParkingLotException extends RuntimeException {

  private Integer status;

  public ParkingLotException() {
    super();
  }

  public ParkingLotException(final String message) {
    super(message);
  }

  public ParkingLotException(final String message, final Integer status) {
    super(message);
    this.status = status;
  }

  public ParkingLotException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ParkingLotException(final Throwable cause) {
    super(cause);
  }

  protected ParkingLotException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
