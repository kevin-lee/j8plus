package cc.kevinlee.functional.spicy;

import cc.kevinlee.functional.types.*;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-22
 */
public class Curry {

  private Curry() {
  }

  public static <T, U, R> Function<U, R> currying(final BiFunction<T, U, R> function, T first) {
    Objects.requireNonNull(function);
    return second -> function.apply(first, second);
  }

  public static <T1, T2, T3, R> Function2<T2, T3, R> currying(final Function3<T1, T2, T3, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, R> Function3<T2, T3, T4, R> currying(final Function4<T1, T2, T3, T4, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, R> Function4<T2, T3, T4, T5, R> currying(final Function5<T1, T2, T3, T4, T5, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Function5<T2, T3, T4, T5, T6, R> currying(final Function6<T1, T2, T3, T4, T5, T6, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Function6<T2, T3, T4, T5, T6, T7, R> currying(final Function7<T1, T2, T3, T4, T5, T6, T7, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function7<T2, T3, T4, T5, T6, T7, T8, R> currying(final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function8<T2, T3, T4, T5, T6, T7, T8, T9, R> currying(final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Function9<T2, T3, T4, T5, T6, T7, T8, T9, T10, R> currying(final Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> function, T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }
}
