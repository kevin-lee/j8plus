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
import java.util.Objects;
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
    Objects.requireNonNull(predicate, "The predicate: Predicate<T> cannot be null.");
    return predicate.negate();
  }

  public static <T> Comparator<T> reversed(final Comparator<T> comparator) {
    Objects.requireNonNull(comparator, "The comparator: Comparator<T> cannot be null.");
    return comparator.reversed();
  }

  /* @formatter:off */
  /**
   * Returns a Higher-order function to create a {@link Comparator} object which takes some objects of type T then use
   * the given toComparable mapper which is a {@link Function} object to map the T type to something {@link Comparable}.
   * Once mapping happened, it uses the comparable objects to compare. This can be very useful and helpful when using
   * with method reference for sorting.
   *
   * e.g.)
   *
   * <pre>
   * list.stream()
   *     .sorted((someBean1, someBean2) -&gt; someBean1.getDateCreated()
   *                                                .compareTo(someBean2.getDateCreated()))
   *     .collect(toList());
   * </pre>
   *
   * can become like
   *
   * <pre>
   * list.stream()
   *     .sorted(compare(SomeBean::getDateCreated))
   *     .collect(toList());
   * </pre>
   *
   * It can be used with the {@link #reversed(Comparator)} method to sort in reversed order.
   *
   * e.g.)
   *
   * <pre>
   * list.stream()
   *     .sorted(reversed(compare(SomeBean::getDateCreated)))
   *     .collect(toList());
   * </pre>
   *
   * @param toComparable
   * @return a Higher-order function the result of which is a {@link Comparator} taking two objects of type T then use
   *         the mapper which is passed as the parameter of this {@link #compare(Function)} method to map the T type to
   *         something {@link Comparable} and use them to compare.
   */
  public static <T, C extends Comparable<C>> Comparator<T> compare(final Function<T, C> toComparable) {
    Objects.requireNonNull(toComparable, "The toComparable: Function<T, C> cannot be null.");
    return (t1, t2) -> toComparable.apply(t1)
                                   .compareTo(toComparable.apply(t2));
  }
  /* @formatter:on */

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
    Objects.requireNonNull(function, "function: Function<T, R> cannot be null.");
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
