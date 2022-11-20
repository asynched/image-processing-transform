package com.unip.pdi.functional;

public class Result<T, E extends Exception> {
  private final T value;
  private final E error;

  private Result(T value, E error) {
    this.value = value;
    this.error = error;
  }

  /**
   * Helper method to check if the variant of the result is of the Ok variant.
   * 
   * @return True if the result is of the Ok variant, false otherwise.
   */
  public boolean isOk() {
    return error == null;
  }

  /**
   * Helper method to check if the variant of the result is of the Err variant.
   * 
   * @return True if the result is of the Err variant, false otherwise.
   */
  public boolean isErr() {
    return error != null;
  }

  /**
   * Helper method to unwrap the inner value of the Result if it is of the Ok
   * variant.
   * 
   * @return The inner value of the Ok variant.
   */
  public T unwrap() {
    if (isOk()) {
      return value;
    } else {
      throw new RuntimeException(error);
    }
  }

  /**
   * Helper method to unwrap the inner value of the Result if it is of the Err
   * variant,
   * 
   * @return The inner value of the Err variant.
   */
  public E unwrapErr() {
    if (isErr()) {
      return error;
    } else {
      throw new RuntimeException("Result is Ok");
    }
  }

  /**
   * Helper method to unwrap the inner value of the Result if it is of the Ok
   * variant,
   * 
   * @param message The message to be used in the exception.
   * @return The inner value of the Ok variant.
   */
  public T expect(String message) {
    if (isOk()) {
      return value;
    } else {
      throw new RuntimeException(message);
    }
  }

  /**
   * Helper method to unwrap the inner value of the Result if it is of the Err
   * variant,
   * 
   * @param message The message to be used in the exception.
   * @return The inner value of the Err variant.
   */
  public E expectError(String message) {
    if (isErr()) {
      return error;
    } else {
      throw new RuntimeException(message);
    }
  }

  /**
   * Creates a Result of the Ok variant.
   * 
   * @param <T>   The type of the inner value.
   * @param <E>   The type of the error.
   * @param value The inner value.
   * @return A Result of the Ok variant.
   */
  public static <T, E extends Exception> Result<T, E> ok(T value) {
    return new Result<T, E>(value, null);
  }

  /**
   * Creates a Result of the Err variant.
   * 
   * @param <T>   The type of the inner value.
   * @param <E>   The type of the error.
   * @param error The inner value.
   * @return A Result of the Err variant.
   */
  public static <T, E extends Exception> Result<T, E> err(E error) {
    return new Result<T, E>(null, error);
  }

  /**
   * Helper method to execute an otherwise unsafe function into a safe (Result)
   * function.
   * 
   * @param <T> The type of the return value.
   * @param <E> The type of the error.
   * @param fn  The unsafe function.
   * @return The result of the function.
   */
  @SuppressWarnings("unchecked")
  public static <T, E extends Exception> Result<T, E> safeFunction(IUnsafeFunction<T, E> fn) {
    try {
      return ok(fn.call());
    } catch (Exception e) {
      return err((E) e);
    }
  }
}
