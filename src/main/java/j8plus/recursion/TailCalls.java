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
package j8plus.recursion;

import java.util.stream.Stream;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-14)
 */
public final class TailCalls {

  /**
   * This is a class that contains the result of tailcallable operation.
   *
   * @author Lee, SeongHyun (Kevin)
   * @version 0.0.1 (2014-06-14)
   *
   */
  private static class Result<T> implements TailCallable<T> {
    private final T value;

    public Result(final T value) {
      this.value = value;
    }

    @Override
    public TailCallable<T> next() {
      throw new RuntimeException("No more next value. It has the result.");
    }

    @Override
    public boolean isDone() {
      return true;
    }

    @Override
    public T result() {
      return value;
    }
  }

  private TailCalls() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + " cannot be instantiated.");
  }

  /**
   * Returns the result of the end operation starting from the given {@link TailCallable}. It keeps running the
   * {@link TailCallable#next()} method and get the next {@link TailCallable} to run the next operation as long as
   * {@link TailCallable#isDone()} returns false. Once any {@link TailCallable#isDone()} returns false, it takes the
   * result from that {@link TailCallable} object then returns it.
   *
   * @param firstTailCallable
   *          The first {@link TailCallable} object normally the one directly from the recursive method.
   * @param <T> the result type, T
   * @return the result of the end operation starting from the given {@link TailCallable}.
   */
  public static <T> T trampoline(final TailCallable<T> firstTailCallable) {
    // @formatter:off
    return Stream.iterate(firstTailCallable, TailCallable::next)
                 .filter(TailCallable::isDone)
                 .findFirst()
                 .get()
                 .result();
    // @formatter:on

    // TailCallable<T> tailCall = firstTailCallable;
    // while (!tailCall.isDone())
    // {
    // tailCall = tailCall.next();
    // }
    // return tailCall.result();
  }

  public static <T> TailCallable<T> done(final T value) {
    return new Result<>(value);
  }
}
