package com.unip.pdi.functional;

public class Option<T> {
  private final T data;

  private Option(T data) {
    this.data = data;
  }

  public boolean isSome() {
    return data != null;
  }

  public boolean isNone() {
    return data == null;
  }

  public T unwrap() {
    if (isSome()) {
      return data;
    } else {
      throw new RuntimeException("Option is None");
    }
  }

  public T expect(String message) {
    if (isSome()) {
      return data;
    } else {
      throw new RuntimeException(message);
    }
  }

  public static <T> Option<T> some(T data) {
    return new Option<T>(data);
  }

  public static <T> Option<T> none() {
    return new Option<T>(null);
  }
}
