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
import java.util.function.Predicate;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-20)
 *
 */
public final class Functions {
  public static final Predicate<?> IS_NULL = t -> t == null;
  public static final Predicate<?> IS_NOT_NULL = IS_NULL.negate();

  private Functions() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

  public static <T> Predicate<T> isNull() {
    @SuppressWarnings("unchecked")
    final Predicate<T> isNull = (Predicate<T>) IS_NULL;
    return isNull;
  }

  public static <T> Predicate<T> isNotNull() {
    @SuppressWarnings("unchecked")
    final Predicate<T> isNull = (Predicate<T>) IS_NOT_NULL;
    return isNull;
  }

  public static <T> Predicate<T> not(final Predicate<T> predicate) {
    return predicate.negate();
  }

  public static <T> Comparator<T> reversed(final Comparator<T> comparator) {
    return comparator.reversed();
  }
}
