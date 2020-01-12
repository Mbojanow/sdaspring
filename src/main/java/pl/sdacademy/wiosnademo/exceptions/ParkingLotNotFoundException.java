package pl.sdacademy.wiosnademo.exceptions;

public class ParkingLotNotFoundException extends RuntimeException {

  public ParkingLotNotFoundException() {
    super();
  }

  public ParkingLotNotFoundException(final String message) {
    super(message);
  }

  public ParkingLotNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ParkingLotNotFoundException(final Throwable cause) {
    super(cause);
  }

  protected ParkingLotNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
