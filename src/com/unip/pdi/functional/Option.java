package com.unip.pdi.functional;

public class Option<T> {
  private final T data;

  private Option(T data) {
    this.data = data;
  }

  /**
   * Helper method to check if the variant of the option is some.
   * 
   * @return True if the option is some, false otherwise.
   */
  public boolean isSome() {
    return data != null;
  }

  /**
   * Helper method to check if the variant of the option is none.
   * 
   * @return True if the option is none, false otherwise.
   */
  public boolean isNone() {
    return data == null;
  }

  /**
   * Helper method to unwrap the inner value of the Option if it is some.
   * 
   * @return The inner value of the Option.
   */
  public T unwrap() {
    if (isSome()) {
      return data;
    } else {
      throw new RuntimeException("Option is None");
    }
  }

  /**
   * Helper method to unwrap the inner value of the Option if it is some,
   * otherwise it throws an error with the provided message.
   * 
   * @param message The message to be used in the exception.
   * @return The inner value of the Option.
   */
  public T expect(String message) {
    if (isSome()) {
      return data;
    } else {
      throw new RuntimeException(message);
    }
  }

  /**
   * Creates an Option of the Some variant.
   * 
   * @param <T>  The type of the inner value.
   * @param data The inner value.
   * @return An Option of the Some variant.
   */
  public static <T> Option<T> some(T data) {
    return new Option<T>(data);
  }

  /**
   * Creates an Option of the None variant.
   * 
   * @param <T> The type of the inner value.
   * @return An Option of the None variant.
   */
  public static <T> Option<T> none() {
    return new Option<T>(null);
  }
}
