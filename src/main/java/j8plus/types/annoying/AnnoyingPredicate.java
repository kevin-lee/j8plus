package j8plus.types.annoying;

import java.util.Objects;

/**
 * @author Kevin Lee
 * @since 2019-11-17
 */
@FunctionalInterface
public interface AnnoyingPredicate<T> {

  /**
   * Evaluates this predicate on the given argument.
   *
   * @param t the input argument
   * @return {@code true} if the input argument matches the predicate,
   * otherwise {@code false}
   * @throws Exception
   */
  boolean test(T t) throws Exception;

  /**
   * Returns a composed AnnoyingPredicate that represents a short-circuiting logical
   * AND of this AnnoyingPredicate and another.  When evaluating the composed
   * predicate, if this AnnoyingPredicate is {@code false}, then the {@code other}
   * AnnoyingPredicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either AnnoyingPredicate are relayed
   * to the caller; if evaluation of this AnnoyingPredicate throws an exception, the
   * {@code other} AnnoyingPredicate will not be evaluated.
   *
   * @param other an AnnoyingPredicate that will be logically-ANDed with this
   *              AnnoyingPredicate
   * @return a composed AnnoyingPredicate that represents the short-circuiting logical
   * AND of this AnnoyingPredicate and the {@code other} AnnoyingPredicate
   * @throws NullPointerException if other is null
   */
  default AnnoyingPredicate<T> and(AnnoyingPredicate<? super T> other) {
    Objects.requireNonNull(other);
    return (t) -> test(t) && other.test(t);
  }

  /**
   * Returns an AnnoyingPredicate that represents the logical negation of this
   * AnnoyingPredicate.
   *
   * @return an AnnoyingPredicate that represents the logical negation of this
   * AnnoyingPredicate
   */
  default AnnoyingPredicate<T> negate() {
    return (t) -> !test(t);
  }

  /**
   * Returns a composed AnnoyingPredicate that represents a short-circuiting logical
   * OR of this AnnoyingPredicate and another.  When evaluating the composed
   * AnnoyingPredicate, if this AnnoyingPredicate is {@code true}, then the {@code other}
   * AnnoyingPredicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either AnnoyingPredicate are relayed
   * to the caller; if evaluation of this AnnoyingPredicate throws an exception, the
   * {@code other} AnnoyingPredicate will not be evaluated.
   *
   * @param other an AnnoyingPredicate that will be logically-ORed with this
   *              AnnoyingPredicate
   * @return a composed AnnoyingPredicate that represents the short-circuiting logical
   * OR of this AnnoyingPredicate and the {@code other} AnnoyingPredicate
   * @throws NullPointerException if other is null
   */
  default AnnoyingPredicate<T> or(AnnoyingPredicate<? super T> other) {
    Objects.requireNonNull(other);
    return (t) -> test(t) || other.test(t);
  }

  /**
   * Returns an AnnoyingPredicate that tests if two arguments are equal according
   * to {@link Objects#equals(Object, Object)}.
   *
   * @param <T> the type of arguments to the AnnoyingPredicate
   * @param targetRef the object reference with which to compare for equality,
   *               which may be {@code null}
   * @return an AnnoyingPredicate that tests if two arguments are equal according
   * to {@link Objects#equals(Object, Object)}
   */
  static <T> AnnoyingPredicate<T> isEqual(Object targetRef) {
    return (null == targetRef)
      ? Objects::isNull
      : targetRef::equals;
  }
}
