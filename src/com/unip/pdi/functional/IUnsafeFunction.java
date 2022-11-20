package com.unip.pdi.functional;

public interface IUnsafeFunction<T, E extends Exception> {
  T call() throws E;
}
