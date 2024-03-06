package com.starteo.api.repository.model.exception;

public class ForbiddenException extends ApiException {
  public ForbiddenException() {
    super(ExceptionType.CLIENT_EXCEPTION, "Access is denied");
  }

  public ForbiddenException(String message) {
    super(ExceptionType.CLIENT_EXCEPTION, message);
  }
}
