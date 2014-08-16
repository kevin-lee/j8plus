/**
 * Copyright 2014 Seong Hyun (Kevin)
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
package cc.kevinlee.functional.types;

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
 * @param <T5>
 *          input5
 */
@FunctionalInterface
public interface Predicate5<T1, T2, T3, T4, T5> {
  boolean test(final T1 input1, final T2 input2, final T3 input3, final T4 input4, final T5 input5);

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
  default Predicate5<T1, T2, T3, T4, T5> and(final Predicate5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> other) {
    Objects.requireNonNull(other);
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4, final T5 input5) ->
          test(input1, input2, input3, input4, input5) &&
          other.test(input1, input2, input3, input4, input5);
    /* @formatter:on */
  }

  /**
   * Returns a predicate that represents the logical negation of this predicate.
   *
   * @return a predicate that represents the logical negation of this predicate
   */
  default Predicate5<T1, T2, T3, T4, T5> negate() {
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4, final T5 input5) ->
          !test(input1, input2, input3, input4, input5);
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
  default Predicate5<T1, T2, T3, T4, T5> or(final Predicate5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> other) {
    Objects.requireNonNull(other);
    /* @formatter:off */
    return
        (final T1 input1, final T2 input2, final T3 input3, final T4 input4, final T5 input5) ->
          test(input1, input2, input3, input4, input5) ||
          other.test(input1, input2, input3, input4, input5);
    /* @formatter:on */
  }
}
