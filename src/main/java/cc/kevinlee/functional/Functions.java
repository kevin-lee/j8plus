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
package cc.kevinlee.functional;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-20)
 *
 */
public final class Functions {

  private Functions() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

  public static <T> Predicate<T> isNull() {
    return t -> t == null;
  }

  public static <T> Predicate<T> isNotNull() {
    return t -> t != null;
  }

  public static <T> Predicate<T> not(final Predicate<T> predicate) {
    return predicate.negate();
  }

  public static <T> Comparator<T> reversed(final Comparator<T> comparator) {
    return comparator.reversed();
  }

  /**
   * <pre>
   * T -&gt; R -&gt; String
   * </pre>
   *
   * <pre>
   * public class SomeType {
   *   private final Long id;
   *
   *   public SomeType(final Long id) {
   *     this.id = id;
   *   }
   *
   *   public Long getId() {
   *     return id;
   *   }
   * }
   *
   * final String result = Arrays.asList(new SomeType(1L), new SomeType(2L), new SomeType(3L))
   *     .stream()
   *     .map(toStringOf(SomeType::getId))
   *     .collect(joining(&quot;, &quot;, &quot;(&quot;, &quot;)&quot;));
   * </pre>
   *
   * <pre>
   * result:
   * (1, 2, 3)
   * </pre>
   *
   * @param function
   *          function to map T to R
   * @return A function to map <code>T -> R -> String</code> (using {@link String#valueOf(Object)})
   */
  public static <T, R> Function<T, String> toStringOf(final Function<T, R> function) {
    /* @formatter:off */
    return input -> function.andThen(String::valueOf)
                            .apply(input);
    /* @formatter:on */
  }

  public static <T> Function<T, Boolean> toTrue() {
    return t -> Boolean.TRUE;
  }

  public static <T> Function<T, Boolean> toFalse() {
    return t -> Boolean.FALSE;
  }
}
