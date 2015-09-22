/**
 * Copyright 2014 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cc.kevinlee.functional;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;

import cc.kevinlee.functional.types.Consumer10;
import cc.kevinlee.functional.types.Consumer3;
import cc.kevinlee.functional.types.Consumer4;
import cc.kevinlee.functional.types.Consumer5;
import cc.kevinlee.functional.types.Consumer6;
import cc.kevinlee.functional.types.Consumer7;
import cc.kevinlee.functional.types.Consumer8;
import cc.kevinlee.functional.types.Consumer9;
import cc.kevinlee.functional.types.Function10;
import cc.kevinlee.functional.types.Function3;
import cc.kevinlee.functional.types.Function4;
import cc.kevinlee.functional.types.Function5;
import cc.kevinlee.functional.types.Function6;
import cc.kevinlee.functional.types.Function7;
import cc.kevinlee.functional.types.Function8;
import cc.kevinlee.functional.types.Function9;
import cc.kevinlee.functional.types.Predicate10;
import cc.kevinlee.functional.types.Predicate3;
import cc.kevinlee.functional.types.Predicate4;
import cc.kevinlee.functional.types.Predicate5;
import cc.kevinlee.functional.types.Predicate6;
import cc.kevinlee.functional.types.Predicate7;
import cc.kevinlee.functional.types.Predicate8;
import cc.kevinlee.functional.types.Predicate9;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-20)
 *
 */
public final class Funs {

  private Funs() {
  }

  public static <T> Predicate<? super T> isNull() {
    return t -> t == null;
  }

  public static <T> Predicate<? super T> isNotNull() {
    return t -> t != null;
  }

  public static <T> Predicate<? super T> not(final Predicate<? super T> predicate) {
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
   *     .sorted(comparing(SomeBean::getDateCreated))
   *     .collect(toList());
   * </pre>
   *
   * It can be used with the {@link #reversed(Comparator)} method to sort in reversed order.
   *
   * e.g.)
   *
   * <pre>
   * list.stream()
   *     .sorted(reversed(comparing(SomeBean::getDateCreated)))
   *     .collect(toList());
   * </pre>
   *
   * @param toComparable Function that can convert the given type T into the Comparable type C.
   * @param <T> The given type
   * @param <C> The comparable type from type T
   *
   * @return a Higher-order function the result of which is a {@link Comparator} taking two objects of type T then use
   *         the mapper which is passed as the parameter of this {@link #comparing(Function)} method to map the T type to
   *         something {@link Comparable} and use them to compare.
   */
  public static <T, C extends Comparable<? super C>> Comparator<T> comparing(final Function<? super T, ? extends C> toComparable) {
    Objects.requireNonNull(toComparable, "The toComparable: Function<T, C> cannot be null.");
    return (t1, t2) -> toComparable.apply(t1)
                                   .compareTo(toComparable.apply(t2));
  }
  /* @formatter:on */

  /**
   * <pre>
   * T -&gt; R -&gt; String
   * </pre>
   * <br>
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
   * <br>
   * <pre>
   * result:
   * (1, 2, 3)
   * </pre>
   *
   * @param function function to map T to R
   * @param <T>      the given type T
   * @param <R>      the mapped type of the given type T. The R type's toString() method is called to return the result.
   * @return A function to map <code>T -&gt; R -&gt; String</code> (using {@link String#valueOf(Object)})
   */
  public static <T, R> Function<? super T, String> toStringOf(final Function<? super T, ? extends R> function) {
    Objects.requireNonNull(function, "function: Function<? super T, ? extends R> cannot be null.");
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

  /* @formatter:off */
  public static <O, T> Predicate<O> satisfying(final BiPredicate<? super O, ? super T> predicate,
                                               final T param1) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2> Predicate<O> satisfying(final Predicate3<? super O, ? super T, ? super T2> predicate,
                                                   final T param1,
                                                   final T2 param2) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3> Predicate<O> satisfying(final Predicate4<? super O, ? super T, ? super T2, ? super T3> predicate,
                                                       final T param1,
                                                       final T2 param2,
                                                       final T3 param3) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4> Predicate<O> satisfying(final Predicate5<? super O, ? super T, ? super T2, ? super T3, ? super T4> predicate,
                                                           final T param1,
                                                           final T2 param2,
                                                           final T3 param3,
                                                           final T4 param4) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5> Predicate<O> satisfying(final Predicate6<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5> predicate,
                                                               final T param1,
                                                               final T2 param2,
                                                               final T3 param3,
                                                               final T4 param4,
                                                               final T5 param5) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4, param5);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6> Predicate<O> satisfying(final Predicate7<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6> predicate,
                                                                   final T param1,
                                                                   final T2 param2,
                                                                   final T3 param3,
                                                                   final T4 param4,
                                                                   final T5 param5,
                                                                   final T6 param6) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4, param5, param6);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7> Predicate<O> satisfying(final Predicate8<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7> predicate,
                                                                       final T param1,
                                                                       final T2 param2,
                                                                       final T3 param3,
                                                                       final T4 param4,
                                                                       final T5 param5,
                                                                       final T6 param6,
                                                                       final T7 param7) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4, param5, param6, param7);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8> Predicate<O> satisfying(final Predicate9<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8> predicate,
                                                                           final T param1,
                                                                           final T2 param2,
                                                                           final T3 param3,
                                                                           final T4 param4,
                                                                           final T5 param5,
                                                                           final T6 param6,
                                                                           final T7 param7,
                                                                           final T8 param8) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4, param5, param6, param7, param8);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8, T9> Predicate<O> satisfying(final Predicate10<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9> predicate,
                                                                               final T param1,
                                                                               final T2 param2,
                                                                               final T3 param3,
                                                                               final T4 param4,
                                                                               final T5 param5,
                                                                               final T6 param6,
                                                                               final T7 param7,
                                                                               final T8 param8,
                                                                               final T9 param9) {
    Objects.requireNonNull(predicate, "The function cannot be null.");
    return object -> predicate.test(object, param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }
  /* @formatter:on */

  public static <O, T, R> Function<O, R> applying(final BiFunction<? super O, ? super T, ? extends R> function, final T param) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param);
  }

  public static <O, T, T2, R> Function<O, R> applying(final Function3<? super O, ? super T, ? super T2, ? extends R> function, final T param1, final T2 param2) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2);
  }

  /* @formatter:off */
  public static <O, T, T2, T3, R> Function<O, R> applying(final Function4<? super O, ? super T, ? super T2, ? super T3, ? extends R> function,
                                                          final T param1,
                                                          final T2 param2,
                                                          final T3 param3) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, R> Function<O, R> applying(final Function5<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? extends R> function,
                                                              final T param1,
                                                              final T2 param2,
                                                              final T3 param3,
                                                              final T4 param4) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, R> Function<O, R> applying(final Function6<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> function,
                                                                  final T param1,
                                                                  final T2 param2,
                                                                  final T3 param3,
                                                                  final T4 param4,
                                                                  final T5 param5) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4, param5);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, R> Function<O, R> applying(final Function7<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> function,
                                                                      final T param1,
                                                                      final T2 param2,
                                                                      final T3 param3,
                                                                      final T4 param4,
                                                                      final T5 param5,
                                                                      final T6 param6) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4, param5, param6);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, R> Function<O, R> applying(final Function8<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> function,
                                                                          final T param1,
                                                                          final T2 param2,
                                                                          final T3 param3,
                                                                          final T4 param4,
                                                                          final T5 param5,
                                                                          final T6 param6,
                                                                          final T7 param7) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4, param5, param6, param7);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8, R> Function<O, R> applying(final Function9<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> function,
                                                                              final T param1,
                                                                              final T2 param2,
                                                                              final T3 param3,
                                                                              final T4 param4,
                                                                              final T5 param5,
                                                                              final T6 param6,
                                                                              final T7 param7,
                                                                              final T8 param8) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4, param5, param6, param7, param8);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8, T9, R> Function<O, R> applying(final Function10<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> function,
                                                                                  final T param1,
                                                                                  final T2 param2,
                                                                                  final T3 param3,
                                                                                  final T4 param4,
                                                                                  final T5 param5,
                                                                                  final T6 param6,
                                                                                  final T7 param7,
                                                                                  final T8 param8,
                                                                                  final T9 param9) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.apply(object, param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }
  /* @formatter:on */

  public static <O, T> Consumer<O> accepting(final BiConsumer<? super O, ? super T> function, final T param) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param);
  }

  public static <O, T, T2> Consumer<O> accepting(final Consumer3<? super O, ? super T, ? super T2> function, final T param1, final T2 param2) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2);
  }

  /* @formatter:off */
  public static <O, T, T2, T3> Consumer<O> accepting(final Consumer4<? super O, ? super T, ? super T2, ? super T3> function,
                                                     final T param1,
                                                     final T2 param2,
                                                     final T3 param3) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4> Consumer<O> accepting(final Consumer5<? super O, ? super T, ? super T2, ? super T3, ? super T4> function,
                                                         final T param1,
                                                         final T2 param2,
                                                         final T3 param3,
                                                         final T4 param4) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5> Consumer<O> accepting(final Consumer6<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5> function,
                                                             final T param1,
                                                             final T2 param2,
                                                             final T3 param3,
                                                             final T4 param4,
                                                             final T5 param5) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4, param5);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6> Consumer<O> accepting(final Consumer7<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6> function,
                                                                 final T param1,
                                                                 final T2 param2,
                                                                 final T3 param3,
                                                                 final T4 param4,
                                                                 final T5 param5,
                                                                 final T6 param6) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4, param5, param6);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7> Consumer<O> accepting(final Consumer8<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7> function,
                                                                     final T param1,
                                                                     final T2 param2,
                                                                     final T3 param3,
                                                                     final T4 param4,
                                                                     final T5 param5,
                                                                     final T6 param6,
                                                                     final T7 param7) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4, param5, param6, param7);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8> Consumer<O> accepting(final Consumer9<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8> function,
                                                                         final T param1,
                                                                         final T2 param2,
                                                                         final T3 param3,
                                                                         final T4 param4,
                                                                         final T5 param5,
                                                                         final T6 param6,
                                                                         final T7 param7,
                                                                         final T8 param8) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4, param5, param6, param7, param8);
  }
  /* @formatter:on */

  /* @formatter:off */
  public static <O, T, T2, T3, T4, T5, T6, T7, T8, T9> Consumer<O> accepting(final Consumer10<? super O, ? super T, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9> function,
                                                                             final T param1,
                                                                             final T2 param2,
                                                                             final T3 param3,
                                                                             final T4 param4,
                                                                             final T5 param5,
                                                                             final T6 param6,
                                                                             final T7 param7,
                                                                             final T8 param8,
                                                                             final T9 param9) {
    Objects.requireNonNull(function, "The function cannot be null.");
    return object -> function.accept(object, param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }
  /* @formatter:on */


    /**
     * 기본으로 제공되는 list.stream().distinct() 는 특정 property 를 지정할 수 없기 때문에,
     * 특정 property 를 지정하여 중복을 제거하는 함수를 추가.
     *
     * persons.stream().filter(distinctByKey(p -> p.getName()) 와 같이 호출하면 됨.
     *
     * @param keyExtractor 중복제거 키 추출 함수
     * @param <T>
     * @return 중복인지 아닌지 판별하는 Predicate 함수
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();

        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
