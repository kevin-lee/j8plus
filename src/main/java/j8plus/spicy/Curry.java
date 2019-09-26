package j8plus.spicy;

import j8plus.types.*;

import java.util.Objects;
import java.util.function.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-22
 */
public class Curry {

  private Curry() {
  }

  public static <T, U, R> Function<U, R> currying(final BiFunction<T, U, R> function, final T first) {
    Objects.requireNonNull(function);
    return second -> function.apply(first, second);
  }

  public static <T1, T2, T3, R> Function2<T2, T3, R> currying(final Function3<T1, T2, T3, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, R> Function3<T2, T3, T4, R> currying(final Function4<T1, T2, T3, T4, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, R> Function4<T2, T3, T4, T5, R> currying(final Function5<T1, T2, T3, T4, T5, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Function5<T2, T3, T4, T5, T6, R> currying(final Function6<T1, T2, T3, T4, T5, T6, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Function6<T2, T3, T4, T5, T6, T7, R> currying(final Function7<T1, T2, T3, T4, T5, T6, T7, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Function7<T2, T3, T4, T5, T6, T7, T8, R> currying(final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function8<T2, T3, T4, T5, T6, T7, T8, T9, R> currying(final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Function9<T2, T3, T4, T5, T6, T7, T8, T9, T10, R> currying(final Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> function, final T1 t1) {
    Objects.requireNonNull(function);
    return function.curried(t1);
  }

  public static <T1, T2> Predicate<T2> currying(final BiPredicate<T1, T2> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return t2 -> predicate.test(t1, t2);
  }

  public static <T1, T2, T3> Predicate2<T2, T3> currying(final Predicate3<T1, T2, T3> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4> Predicate3<T2, T3, T4> currying(final Predicate4<T1, T2, T3, T4> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5> Predicate4<T2, T3, T4, T5> currying(final Predicate5<T1, T2, T3, T4, T5> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6> Predicate5<T2, T3, T4, T5, T6> currying(final Predicate6<T1, T2, T3, T4, T5, T6> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Predicate6<T2, T3, T4, T5, T6, T7> currying(final Predicate7<T1, T2, T3, T4, T5, T6, T7> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Predicate7<T2, T3, T4, T5, T6, T7, T8> currying(final Predicate8<T1, T2, T3, T4, T5, T6, T7, T8> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Predicate8<T2, T3, T4, T5, T6, T7, T8, T9> currying(final Predicate9<T1, T2, T3, T4, T5, T6, T7, T8, T9> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Predicate9<T2, T3, T4, T5, T6, T7, T8, T9, T10> currying(final Predicate10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> predicate, final T1 t1) {
    Objects.requireNonNull(predicate);
    return predicate.curried(t1);
  }


  public static <T1, T2> Consumer<T2> currying(final BiConsumer<T1, T2> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return t2 -> consumer.accept(t1, t2);
  }

  public static <T1, T2, T3> Consumer2<T2, T3> currying(final Consumer3<T1, T2, T3> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4> Consumer3<T2, T3, T4> currying(final Consumer4<T1, T2, T3, T4> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5> Consumer4<T2, T3, T4, T5> currying(final Consumer5<T1, T2, T3, T4, T5> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6> Consumer5<T2, T3, T4, T5, T6> currying(final Consumer6<T1, T2, T3, T4, T5, T6> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7> Consumer6<T2, T3, T4, T5, T6, T7> currying(final Consumer7<T1, T2, T3, T4, T5, T6, T7> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8> Consumer7<T2, T3, T4, T5, T6, T7, T8> currying(final Consumer8<T1, T2, T3, T4, T5, T6, T7, T8> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Consumer8<T2, T3, T4, T5, T6, T7, T8, T9> currying(final Consumer9<T1, T2, T3, T4, T5, T6, T7, T8, T9> consumer, final T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Consumer9<T2, T3, T4, T5, T6, T7, T8, T9, T10> currying(final Consumer10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> consumer, T1 t1) {
    Objects.requireNonNull(consumer);
    return consumer.curried(t1);
  }

}
