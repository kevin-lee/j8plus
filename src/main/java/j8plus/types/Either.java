package j8plus.types;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-03
 */
public abstract class Either<A, B> implements Serializable {

  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract <C> Either<A, C> map(Function<? super B, C> f);

  public abstract <C> Either<A, C> flatMap(Function<? super B, Either<A, C>> f);

  public abstract <C> Either<C, B> leftMap(Function<? super A, C> f);

  public abstract <C> Either<C, B> leftFlatMap(Function<? super A, Either<C, B>> f);

  public Either<B, A> swap() {
    return Either.swap(this);
  }

  public <C> C fold(final Function<? super A, C> leftFunction, final Function<? super B, C> rightFunction) {
    return isLeft() ?
        leftFunction.apply(toLeft(this).value) :
        rightFunction.apply(((Right<A, B>) this).value);
  }

  public static <A, B> Either<B, A> swap(final Either<A, B> either) {
    if (either.isLeft()) {
      return Either.right(toLeft(either).value);
    }
    return Either.left(toRight(either).value);
  }

  public static <A, B> Either<A, B> left(final A value) {
    return Either.left(value);
  }

  public static <A, B> Either<A, B> right(final B value) {
    return Either.right(value);
  }


  private static <A, B> Left<A, B> toLeft(final Either<A, B> either) {
    return (Left<A, B>) either;
  }

  private static <A, B> Right<A, B> toRight(final Either<A, B> either) {
    return (Right<A, B>) either;
  }

  private static class Left<A, B> extends Either<A, B> {
    final A value;

    public Left(A value) {
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
    public <C> Either<A, C> map(final Function<? super B, C> f) {
      return (Either<A, C>) this;
    }

    @Override
    public <C> Either<A, C> flatMap(final Function<? super B, Either<A, C>> f) {
      return (Either<A, C>) this;
    }

    @Override
    public <C> Either<C, B> leftMap(final Function<? super A, C> f) {
      return Either.left(f.apply(this.value));
    }

    @Override
    public <C> Either<C, B> leftFlatMap(final Function<? super A, Either<C, B>> f) {
      return f.apply(this.value);
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

  private static class Right<A, B> extends Either<A, B> {
    final B value;

    public Right(B value) {
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
    public <C> Either<A, C> map(final Function<? super B, C> f) {
      return Either.right(f.apply(this.value));
    }

    @Override
    public <C> Either<A, C> flatMap(final Function<? super B, Either<A, C>> f) {
      return f.apply(this.value);
    }

    @Override
    public <C> Either<C, B> leftMap(final Function<? super A, C> f) {
      return (Either<C, B>) this;
    }

    @Override
    public <C> Either<C, B> leftFlatMap(final Function<? super A, Either<C, B>> f) {
      return (Either<C, B>) this;
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

