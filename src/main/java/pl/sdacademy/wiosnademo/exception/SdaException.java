package pl.sdacademy.wiosnademo.exception;

import lombok.Getter;

@Getter
public class SdaException extends RuntimeException {

  private final int status;

  public SdaException(final int status) {
    super();
    this.status = status;
  }

  public SdaException(final String message, final int status) {
    super(message);
    this.status = status;
  }

  public SdaException(final String message, final Throwable cause, final int status) {
    super(message, cause);
    this.status = status;
  }

  public SdaException(final Throwable cause, final int status) {
    super(cause);
    this.status = status;
  }

  protected SdaException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace, final int status) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.status = status;
  }
}
