package com.unip.pdi.functional;

public interface IFunction<TArg, TOut> {
  TOut call(TArg arg);
}
