package com.starteo.api.repository.model.exception;

public class NotImplementedException extends ApiException {
  public NotImplementedException(String message) {
    super(ExceptionType.SERVER_EXCEPTION, message);
  }
}
