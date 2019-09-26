/**
 * Copyright 2014 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package j8plus.types;

import java.util.Objects;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-13)
 * @param <T1>
 *          input1
 * @param <T2>
 *          input2
 * @param <T3>
 *          input3
 * @param <T4>
 *          input4
 */
@FunctionalInterface
public interface Predicate4<T1, T2, T3, T4> {

  /**
   * valuates this predicate on the given arguments.
   *
   * @param input1 the first input argument
   * @param input2 the second input argument
   * @param input3 the third input argument
   * @param input4 the fourth input argument
   * @return {@code true} if the input arguments match the predicate,
   * otherwise {@code false}
   */
  boolean test(final T1 input1, final T2 input2, final T3 input3, final T4 input4);

  /**
   * Given this PredicateN, it returns a curried Predicate(N-1) where the given first input value is set.
   *
   * @param t1 the first input value.
   * @return Predicate(N-1) where N is from this PredicateN.
   * If this predicate is Predicate10, it returns the curried Predicate9.
   * If it is Predicate3, it returns the curried Predicate2 (not BiPredicate).
   * If it is Predicate2, it returns the curried Predicate.
   */
  default Predicate3<T2, T3, T4> curried(final T1 t1) {
    return (t2, t3, t4) -> test(t1, t2, t3, t4);
  }

  /**
   * Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another. When
   * evaluating the composed predicate, if this predicate is {@code false}, then the {@code other} predicate is not
   * evaluated.
   *
   * <p>
   * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
   * predicate throws an exception, the {@code other} predicate will not be evaluated.
   *
   * @param other
   *          a predicate that will be logically-ANDed with this predicate
   * @return a composed predicate that represents the short-circuiting logical AND of this predicate and the
   *         {@code other} predicate
   * @throws NullPointerException
   *           if other is null
   */
  default Predicate4<T1, T2, T3, T4> and(final Predicate4<? super T1, ? super T2, ? super T3, ? super T4> other) {
    Objects.requireNonNull(other);
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4) ->
          test(input1, input2, input3, input4) &&
          other.test(input1, input2, input3, input4);
    /* @formatter:on */
  }

  /**
   * Returns a predicate that represents the logical negation of this predicate.
   *
   * @return a predicate that represents the logical negation of this predicate
   */
  default Predicate4<T1, T2, T3, T4> negate() {
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4) ->
          !test(input1, input2, input3, input4);
    /* @formatter:on */
  }

  /**
   * Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another. When
   * evaluating the composed predicate, if this predicate is {@code true}, then the {@code other} predicate is not
   * evaluated.
   *
   * <p>
   * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
   * predicate throws an exception, the {@code other} predicate will not be evaluated.
   *
   * @param other
   *          a predicate that will be logically-ORed with this predicate
   * @return a composed predicate that represents the short-circuiting logical OR of this predicate and the
   *         {@code other} predicate
   * @throws NullPointerException
   *           if other is null
   */
  default Predicate4<T1, T2, T3, T4> or(final Predicate4<? super T1, ? super T2, ? super T3, ? super T4> other) {
    Objects.requireNonNull(other);
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4) ->
          test(input1, input2, input3, input4) ||
          other.test(input1, input2, input3, input4);
    /* @formatter:on */
  }
}
