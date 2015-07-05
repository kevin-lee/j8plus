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

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-30)
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
public interface Consumer5<T1, T2, T3, T4, T5> {

  void accept(T1 input1, T2 input2, T3 input3, T4 input4, T5 input5);

  /**
   * Given this ConsumerN, it returns a curried Consumer(N-1) where the given first input value is set.
   *
   * @param t1 the first input value.
   * @return Consumer(N-1) where N is from this ConsumerN (e.g. Consumer10 -&gt; N = 10).
   * If this consumer is Consumer10, it returns the curried Consumer9.
   * If it is Consumer3, it returns the curried Consumer2 (not BiConsumer).
   * If it is Consumer2, it returns the curried Consumer.
   */
  default Consumer4<T2, T3, T4, T5> curried(final T1 t1) {
    return (t2, t3, t4, t5) -> accept(t1, t2, t3, t4, t5);
  }

  /**
   * Returns a composed {@code Consumer} that performs, in sequence, this operation followed by the {@code after}
   * operation. If performing either operation throws an exception, it is relayed to the caller of the composed
   * operation. If performing this operation throws an exception, the {@code after} operation will not be performed.
   *
   * @param after
   *          the operation to perform after this operation
   * @return a composed {@code Consumer} that performs in sequence this operation followed by the {@code after}
   *         operation
   * @throws NullPointerException
   *           if {@code after} is null
   */
  default Consumer5<T1, T2, T3, T4, T5> andThen(Consumer5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> after) {
    Objects.requireNonNull(after);
    return (input1, input2, input3, input4, input5) -> {
      accept(input1, input2, input3, input4, input5);
      after.accept(input1, input2, input3, input4, input5);
    };
  }
}
