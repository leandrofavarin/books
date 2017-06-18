package com.leandrofavarin.books.utils.rx;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;

public final class Consumers {
  /** Source: https://gist.github.com/dlew/0dc95faa2d3eb923433d */
  public static Consumer<? super Throwable> crashOnError() {
    final Throwable checkpoint = new Throwable();
    return throwable -> {
      StackTraceElement[] stackTrace = checkpoint.getStackTrace();
      StackTraceElement element = stackTrace[1]; // First element after `crashOnError()`
      String msg = String.format("onError() crash from subscribe() in %s.%s(%s:%s)",
          element.getClassName(),
          element.getMethodName(),
          element.getFileName(),
          element.getLineNumber());

      throw new OnErrorNotImplementedException(msg, throwable);
    };
  }

  private Consumers() {
    throw new AssertionError("No instances");
  }
}
