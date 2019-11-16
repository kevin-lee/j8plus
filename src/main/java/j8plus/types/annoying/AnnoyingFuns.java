/**
 * Copyright 2014 Lee, Seong Hyun (Kevin)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package j8plus.types.annoying;

import j8plus.types.Runner;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-24
 */
public class AnnoyingFuns {

  /**
   * Returns Function which does exactly the same as the given AnnoyingFunction but without throwing a checked exception that that annoying one might throw.
   *
   * e.g.)
   * <pre>
   *   &lt;E, T&gt; T doItWithAnnoyance(E whatEver) throws Exception {
   *     throw new Exception("Annoying exception!");
   *   }
   *
   *   &lt;E, T&gt; T doSomething(Function&lt;E, T&gt; function) {
   *     final E someInput = getSomeInput();
   *     return function.apply(someInput);
   *   }
   *
   *   // ...
   *   doSomething(this::doItWithAnnoyance);    // compile time error: Unhandled exception: java.lang.Exception
   *   doSomething(x -&gt; doItWithAnnoyance(x));  // compile time error: Unhandled exception: java.lang.Exception
   *
   *   // but if you use shh(),
   *   doSomething(shh(this::doItWithAnnoyance));    // NO compile time error anymore
   *   doSomething(shh(x -&gt; doItWithAnnoyance(x)));  // NO compile time error anymore
   * </pre>
   *
   * @param function The given AnnoyingFunction which may or may not throw a checked Exception.
   * @param <T> the input type of the function
   * @param <R> the result type of the function
   * @return Function which can handle any checked Exception thrown by the given AnnoyingFunction and wrap it with an unchecked exception that is RuntimeException.
   */
  public static <T, R> Function<T, R> shh(final AnnoyingFunction<T, R> function) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> {
      try {
        return function.apply(object);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  /**
   * Returns BiFunction which does exactly the same as the given AnnoyingBiFunction but without throwing a checked exception that that annoying one might throw.
   *
   * e.g.)
   * <pre>
   *   &lt;T, U, R&gt; R doItWithAnnoyance2(T input1, U input2) throws Exception {
   *     throw new Exception("Annoying exception!");
   *   }
   *
   *   &lt;T, U, R&gt; R doSomething(BiFunction&lt;T, U, R&gt; function) {
   *     // ...
   *     return function.apply(someInput1, someInput2);
   *   }
   *
   *   doSomething(shh(this::doItWithAnnoyance2));            // No compile time error
   *   doSomething(shh((x, y) -&gt; doItWithAnnoyance2(x, y)));  // No compile time error
   * </pre>
   *
   * @param function The given AnnoyingBiFunction which may or may not throw a checked Exception.
   * @param <T> the input1 type of the function
   * @param <U> the input2 type of the function
   * @param <R> the result type of the function
   * @return BiFunction which can handle any checked Exception thrown by the given AnnoyingBiFunction and wrap it with an unchecked exception that is RuntimeException.
   */
  public static <T, U, R> BiFunction<T, U, R> shh(final AnnoyingBiFunction<T, U, R> function) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return (input1, input2) -> {
      try {
        return function.apply(input1, input2);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static Runner shh(final AnnoyingRunnable annoyingRunnable) {
    Objects.requireNonNull(annoyingRunnable, "The given AnnoyingRunner cannot be null.");
    return () -> {
      try {
        annoyingRunnable.run();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static <T> Supplier<T> shh(final AnnoyingSupplier<T> annoyingSupplier) {
    Objects.requireNonNull(annoyingSupplier, "The given AnnoyingSupplier cannot be null.");
    return () -> {
      try {
        return annoyingSupplier.get();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }
}
