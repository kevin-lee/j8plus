package j8plus.types;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

  public abstract <C> Either<A, C> ap(Supplier<Either<A, Function<? super B, C>>> f);

  public abstract Either<B, A> swap();

  public abstract <C> C fold(
    final Function<? super A, C> leftFunction
  , final Function<? super B, C> rightFunction
  );

  public abstract B getOrElse(Supplier<B> alternative);

  public abstract void forEach(Consumer<? super B> f);

  public abstract Optional<B> toOptional();

  public Maybe<B> toMaybe() {
    return Maybe.fromEither(this);
  }

  public static <A, B> Either<A, B> left(final A value) {
    @SuppressWarnings("unchecked")
    final Either<A, B> left = new Left(value);
    return left;
  }

  public static <A, B> Either<A, B> right(final B value) {
    @SuppressWarnings("unchecked")
    final Either<A, B> right = new Right(value);
    return right;
  }

  public static <A, B> Either<A, B> fromOptional(
    final Optional<? extends B> optional
  , final Supplier<? extends A> ifNone
  ) {
    if (optional.isPresent()) {
      return Either.right(optional.get());
    } else {
      return Either.left(ifNone.get());
    }
  }

  public static <A, B> Either<A, B> fromMaybe(
    final Maybe<? extends B> maybe
  , final Supplier<? extends A> ifNone
  ) {
    return maybe.fold(() -> Either.left(ifNone.get()), Either::right);
  }

  static final class Left<A, B> extends Either<A, B> {
    final A value;

    Left(A value) {
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
      @SuppressWarnings("unchecked")
      final Either<A, C> acEither = (Either<A, C>) this;
      return acEither;
    }

    @Override
    public <C> Either<A, C> flatMap(final Function<? super B, Either<A, C>> f) {
      @SuppressWarnings("unchecked")
      final Either<A, C> acEither = (Either<A, C>) this;
      return acEither;
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
    public <C> Either<A, C> ap(final Supplier<Either<A, Function<? super B, C>>> f) {
      @SuppressWarnings("unchecked")
      final Either<A, C> acEither = (Either<A, C>) this;
      return acEither;
    }

    @Override
    public Either<B, A> swap() {
      return Either.right(this.value);
    }

    @Override
    public <C> C fold(
      final Function<? super A, C> leftFunction
    , final Function<? super B, C> rightFunction
    ) {
      return leftFunction.apply(this.value);
    }

    @Override
    public B getOrElse(final Supplier<B> alternative) {
      return alternative.get();
    }

    @Override
    public void forEach(final Consumer<? super B> f) {
    }

    @Override
    public Optional<B> toOptional() {
      return Optional.empty();
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
      return Objects.hashCode(value);
    }

    @Override
    public String toString() {
      return "Left(" + value + ")";
    }
  }

  static final class Right<A, B> extends Either<A, B> {
    final B value;

    Right(B value) {
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
      @SuppressWarnings("unchecked")
      final Either<C, B> cbEither = (Either<C, B>) this;
      return cbEither;
    }

    @Override
    public <C> Either<C, B> leftFlatMap(final Function<? super A, Either<C, B>> f) {
      @SuppressWarnings("unchecked")
      final Either<C, B> cbEither = (Either<C, B>) this;
      return cbEither;
    }

    @Override
    public <C> Either<A, C> ap(final Supplier<Either<A, Function<? super B, C>>> f) {
      return f.get().flatMap(this::map);
    }

    @Override
    public Either<B, A> swap() {
      return Either.left(this.value);
    }

    @Override
    public <C> C fold(
      final Function<? super A, C> leftFunction
    , final Function<? super B, C> rightFunction
    ) {
      return rightFunction.apply(this.value);
    }

    @Override
    public B getOrElse(final Supplier<B> alternative) {
      return this.value;
    }

    @Override
    public void forEach(final Consumer<? super B> f) {
      f.accept(this.value);
    }

    @Override
    public Optional<B> toOptional() {
      return Optional.ofNullable(value);
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
      return Objects.hashCode(value);
    }

    @Override
    public String toString() {
      return "Right(" + value + ")";
    }
  }
}

