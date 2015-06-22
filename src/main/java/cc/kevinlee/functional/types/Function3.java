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
package cc.kevinlee.functional.types;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-11-13)
 * @param <T1>
 *          input1
 * @param <T2>
 *          input2
 * @param <T3>
 *          input3
 * @param <R>
 *          result
 */
@FunctionalInterface
public interface Function3<T1, T2, T3, R> {
  R apply(T1 input1, T2 input2, T3 input3);

  /**
   * Given this FunctionN, it returns a curried Function(N-1) where the given first input value is set.
   *
   * @param t1 the first input value.
   * @return Function(N-1) where N is from this FunctionN.
   * If this function is Function10, it returns the curried Function9.
   * If it is Function2, it returns the curried Function.
   */
  default Function2<T2, T3, R> curried(final T1 t1) {
    return (t2, t3) -> apply(t1, t2, t3);
  }

  /**
   * Returns a composed function that first applies this function to its input, and then applies the {@code after}
   * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
   * composed function.
   *
   * @param <V>
   *          the type of output of the {@code after} function, and of the composed function
   * @param after
   *          the function to apply after this function is applied
   * @return a composed function that first applies this function and then applies the {@code after} function
   * @throws NullPointerException
   *           if after is null
   */
  default <V> Function3<T1, T2, T3, V> andThen(final Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (input1, input2, input3) -> after.apply(apply(input1, input2, input3));
  }
}
