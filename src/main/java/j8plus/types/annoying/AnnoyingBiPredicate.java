package j8plus.types.annoying;

import java.util.Objects;

/**
 * @author Kevin Lee
 * @since 2019-11-17
 */
@FunctionalInterface
public interface AnnoyingBiPredicate<T, U> {

  /**
   * Evaluates this AnnoyingBiPredicate on the given arguments.
   *
   * @param t the first input argument
   * @param u the second input argument
   * @return {@code true} if the input arguments match the AnnoyingBiPredicate,
   * otherwise {@code false}
   * @throws Exception
   */
  boolean test(T t, U u) throws Exception;

  /**
   * Returns a composed AnnoyingBiPredicate that represents a short-circuiting logical
   * AND of this AnnoyingBiPredicate and another.  When evaluating the composed
   * AnnoyingBiPredicate, if this AnnoyingBiPredicate is {@code false}, then the {@code other}
   * AnnoyingBiPredicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either AnnoyingBiPredicate are relayed
   * to the caller; if evaluation of this AnnoyingBiPredicate throws an exception, the
   * {@code other} AnnoyingBiPredicate will not be evaluated.
   *
   * @param other an AnnoyingBiPredicate that will be logically-ANDed with this
   *              AnnoyingBiPredicate
   * @return a composed AnnoyingBiPredicate that represents the short-circuiting logical
   * AND of this AnnoyingBiPredicate and the {@code other} AnnoyingBiPredicate
   * @throws NullPointerException if other is null
   */
  default AnnoyingBiPredicate<T, U> and(AnnoyingBiPredicate<? super T, ? super U> other) {
    Objects.requireNonNull(other);
    return (T t, U u) -> test(t, u) && other.test(t, u);
  }

  /**
   * Returns an AnnoyingBiPredicate that represents the logical negation of this
   * AnnoyingBiPredicate.
   *
   * @return a predicate that represents the logical negation of this
   * predicate
   */
  default AnnoyingBiPredicate<T, U> negate() {
    return (T t, U u) -> !test(t, u);
  }

  /**
   * Returns a composed AnnoyingBiPredicate that represents a short-circuiting logical
   * OR of this AnnoyingBiPredicate and another.  When evaluating the composed
   * AnnoyingBiPredicate, if this AnnoyingBiPredicate is {@code true}, then the {@code other}
   * AnnoyingBiPredicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either AnnoyingBiPredicate are relayed
   * to the caller; if evaluation of this AnnoyingBiPredicate throws an exception, the
   * {@code other} AnnoyingBiPredicate will not be evaluated.
   *
   * @param other an AnnoyingBiPredicate that will be logically-ORed with this
   *              AnnoyingBiPredicate
   * @return a composed AnnoyingBiPredicate that represents the short-circuiting logical
   * OR of this AnnoyingBiPredicate and the {@code other} AnnoyingBiPredicate
   * @throws NullPointerException if other is null
   */
  default AnnoyingBiPredicate<T, U> or(AnnoyingBiPredicate<? super T, ? super U> other) {
    Objects.requireNonNull(other);
    return (T t, U u) -> test(t, u) || other.test(t, u);
  }
}