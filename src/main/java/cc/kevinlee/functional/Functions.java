/**
 *
 */
package cc.kevinlee.functional;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-20)
 *
 */
public final class Functions {
  public static final Predicate<?> IS_NULL = t -> t == null;
  public static final Predicate<?> IS_NOT_NULL = IS_NULL.negate();

  private Functions() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

  public static <T> Predicate<T> isNull() {
    @SuppressWarnings("unchecked")
    final Predicate<T> isNull = (Predicate<T>) IS_NULL;
    return isNull;
  }

  public static <T> Predicate<T> isNotNull() {
    @SuppressWarnings("unchecked")
    final Predicate<T> isNull = (Predicate<T>) IS_NOT_NULL;
    return isNull;
  }

  public static <T> Predicate<T> not(final Predicate<T> predicate) {
    return predicate.negate();
  }

  public static <T> Comparator<T> reversed(final Comparator<T> comparator) {
    return comparator.reversed();
  }
}
