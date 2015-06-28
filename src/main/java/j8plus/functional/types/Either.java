package cc.kevinlee.functional.types;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-03
 */
public abstract class Either<L, R> {

  private Either() {
  }

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public Either<R, L> swap() {
    return Either.swap(this);
  }

  public <Y> Y fold(final Function<? super L, Y> leftFunction, final Function<? super R, Y> rightFunction) {
    return isLeft() ?
        leftFunction.apply(toLeft(this).value) :
        rightFunction.apply(((Right<L, R>) this).value);
  }

  public static <L, R> Either<R, L> swap(final Either<L, R> either) {
    if (either.isLeft()) {
      return Either.right(toLeft(either).value);
    }
    return Either.left(toRight(either).value);
  }

  public LeftProjection<L, R> left() {
    return Either.leftProjection(this);
  }

  public RightProjection<L, R> right() {
    return Either.rightProjection(this);
  }

  public static <L, R> Either<L, R> left(final L value) {
    return Either.left(value);
  }

  public static <L, R> Either<L, R> right(final R value) {
    return Either.right(value);
  }


  static <L, R> Left<L, R> toLeft(final Either<L, R> either) {
    return (Left<L, R>) either;
  }

  static <L, R> Right<L, R> toRight(final Either<L, R> either) {
    return (Right<L, R>) either;
  }

  public static class LeftProjection<L, R> {
    private final Either<L, R> either;

    public LeftProjection(final Either<L, R> either) {
      this.either = either;
    }

    public L get() {
      if (either.isLeft()) {
        return toLeft(either).value;
      }
      throw new NoSuchElementException("No left.value on " + toString());
    }

    public L getOr(final L alternative) {
      if (either.isLeft()) {
        return get();
      }
      return alternative;
    }

    public L getOrElse(final Supplier<L> alternative) {
      if (either.isLeft()) {
        return get();
      }
      return alternative.get();
    }

    public boolean exists(final Predicate<L> predicate) {
      return either.isLeft() ? predicate.test(toLeft(either).value) : false;
    }

    public Optional<Either<L, R>> filter(Predicate<L> predicate) {
      if (either.isLeft()) {
        final Left<L, R> left = toLeft(either);
        if (predicate.test(left.value)) {
          return Optional.ofNullable(Either.left(left.value));
        }
      }
      return Optional.empty();
    }

    public <X> Either<X, R> map(final Function<L, X> mapper) {
      return either.isLeft() ?
          Either.left(mapper.apply(toLeft(either).value)) :
          Either.right(toRight(either).value);
    }

    public <X> Either<X, R> flatMap(final Function<L, Either<X, R>> mapper) {
      return either.isLeft() ?
          mapper.apply(toLeft(either).value) :
          Either.right(toRight(either).value);
    }

    public Optional<L> toOptional() {
      if (either.isLeft()) {
        Optional.ofNullable(toLeft(either).value);
      }
      return Optional.empty();
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof LeftProjection)) return false;
      final LeftProjection<?, ?> that = (LeftProjection<?, ?>) o;
      return Objects.equals(either, that.either);
    }

    @Override
    public int hashCode() {
      return Objects.hash(either);
    }

    @Override
    public String toString() {
      return "LeftProjection(" + either + ")";
    }
  }

  public static class RightProjection<L, R> {
    private final Either<L, R> either;

    public RightProjection(final Either<L, R> either) {
      this.either = either;
    }

    public R get() {
      if (either.isRight()) {
        return ((Right<L, R>) either).value;
      }
      throw new NoSuchElementException("No right.value on " + toString());
    }

    public R getOr(final R alternative) {
      if (either.isRight()) {
        return get();
      }
      return alternative;
    }

    public R getOrElse(final Supplier<R> alternative) {
      if (either.isRight()) {
        return get();
      }
      return alternative.get();
    }

    public boolean exists(final Predicate<R> predicate) {
      return either.isRight() ? predicate.test(toRight(either).value) : false;
    }

    public Optional<Either<L, R>> filter(Predicate<R> predicate) {
      if (either.isRight()) {
        final Right<L, R> right = toRight(either);
        if (predicate.test(right.value)) {
          return Optional.ofNullable(Either.right(right.value));
        }
      }
      return Optional.empty();
    }

    public <X> Either<L, X> map(final Function<R, X> mapper) {
      return either.isRight() ?
          Either.right(mapper.apply(((Right<?, R>) either).value)) :
          Either.left(((Left<L, ?>) either).value);
    }

    public <X> Either<L, X> flatMap(final Function<R, Either<L, X>> mapper) {
      return either.isRight() ?
          mapper.apply(((Right<?, R>) either).value) :
          Either.left(((Left<L, ?>) either).value);
    }

    public Optional<R> toOptional() {
      if (either.isRight()) {
        Optional.ofNullable(toRight(either).value);
      }
      return Optional.empty();
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof RightProjection)) return false;
      final RightProjection<?, ?> that = (RightProjection<?, ?>) o;
      return Objects.equals(either, that.either);
    }

    @Override
    public int hashCode() {
      return Objects.hash(either);
    }

    @Override
    public String toString() {
      return "RightProjection(" + either + ")";
    }
  }

  static <L, R> LeftProjection<L, R> leftProjection(final Either<L, R> either) {
    return new LeftProjection<>(either);
  }


  static <L, R> RightProjection<L, R> rightProjection(final Either<L, R> either) {
    return new RightProjection<>(either);
  }

  static class Left<L, R> extends Either<L, R> {
    final L value;

    public Left(L value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Left)) return false;
      final Left<?, ?> left = (Left<?, ?>) o;
      return Objects.equals(value, left.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    @Override
    public String toString() {
      return "Left(" + value + ")";
    }
  }

  static class Right<L, R> extends Either<L, R> {
    final R value;

    public Right(R value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public boolean isRight() {
      return true;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Right)) return false;
      final Right<?, ?> right = (Right<?, ?>) o;
      return Objects.equals(value, right.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    @Override
    public String toString() {
      return "Right(" + value + ")";
    }
  }
}

