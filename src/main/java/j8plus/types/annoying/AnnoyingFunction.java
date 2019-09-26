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

import java.util.Objects;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-24
 */
@FunctionalInterface
public interface AnnoyingFunction<T, R> {
  R apply(T t) throws Exception;

  default <V> AnnoyingFunction<V, R> compose(AnnoyingFunction<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return (V v) -> apply(before.apply(v));
  }

  default <V> AnnoyingFunction<T, V> andThen(AnnoyingFunction<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (T t) -> after.apply(apply(t));
  }

  static <T> AnnoyingFunction<T, T> identity() {
    return t -> t;
  }
}
