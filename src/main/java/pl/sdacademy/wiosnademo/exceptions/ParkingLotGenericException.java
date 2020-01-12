package pl.sdacademy.wiosnademo.exceptions;

public class ParkingLotGenericException extends RuntimeException {
  public ParkingLotGenericException() {
    super();
  }

  public ParkingLotGenericException(final String message) {
    super(message);
  }

  public ParkingLotGenericException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ParkingLotGenericException(final Throwable cause) {
    super(cause);
  }

  protected ParkingLotGenericException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
