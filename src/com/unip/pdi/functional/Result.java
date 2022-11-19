package com.unip.pdi.functional;

public class Result<T, E extends Exception> {
  private final T value;
  private final E error;

  private Result(T value, E error) {
    this.value = value;
    this.error = error;
  }

  public boolean isOk() {
    return error == null;
  }

  public boolean isError() {
    return error != null;
  }

  public T unwrap() {
    if (isOk()) {
      return value;
    } else {
      throw new RuntimeException(error);
    }
  }

  public E unwrapError() {
    if (isError()) {
      return error;
    } else {
      throw new RuntimeException("Result is Ok");
    }
  }

  public T expect(String message) {
    if (isOk()) {
      return value;
    } else {
      throw new RuntimeException(message);
    }
  }

  public E expectError(String message) {
    if (isError()) {
      return error;
    } else {
      throw new RuntimeException(message);
    }
  }

  public static <T, E extends Exception> Result<T, E> ok(T value) {
    return new Result<T, E>(value, null);
  }

  public static <T, E extends Exception> Result<T, E> err(E error) {
    return new Result<T, E>(null, error);
  }

  @SuppressWarnings("unchecked")
  public static <T, E extends Exception> Result<T, E> safeFunction(IUnsafeFunction<T, E> fn) {
    try {
      return ok(fn.call());
    } catch (Exception e) {
      return err((E) e);
    }
  }
}
