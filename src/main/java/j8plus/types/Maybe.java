package j8plus.types;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Kevin Lee
 * @since 2019-11-13
 */
public abstract class Maybe<A> {

  public abstract boolean isNothing();
  public abstract boolean isJust();

  public abstract <B> Maybe<B> map(Function<? super A, B> f);
  public abstract <B> Maybe<B> flatMap(Function<? super A, Maybe<B>> f);

  public abstract <B> Maybe<B> ap(Supplier<Maybe<Function<? super A, B>>> f);

  public abstract Maybe<A> filter(Predicate<? super A> p);

  public abstract <B> B fold(Function<? super A, B> justCase, Supplier<B> nothingCase);

  public abstract void forEach(Consumer<? super A> f);

  public abstract Maybe<A> orElse(Supplier<Maybe<A>> alternative);

  public abstract A getOrElse(Supplier<A> alternativeValue);

  static final class Nothing extends Maybe<Object> {
    @Override
    public boolean isNothing() {
      return true;
    }

    @Override
    public boolean isJust() {
      return false;
    }

    @Override
    public <B> Maybe<B> map(final Function<? super Object, B> f) {
      @SuppressWarnings("unchecked")
      final Maybe<B> maybeOfB = (Maybe<B>) this;
      return maybeOfB;
    }

    @Override
    public <B> Maybe<B> flatMap(final Function<? super Object, Maybe<B>> f) {
      @SuppressWarnings("unchecked")
      final Maybe<B> maybeOfB = (Maybe<B>) this;
      return maybeOfB;
    }

    @Override
    public <B> Maybe<B> ap(final Supplier<Maybe<Function<? super Object, B>>> f) {
      @SuppressWarnings("unchecked")
      final Maybe<B> maybeOfB = (Maybe<B>) this;
      return maybeOfB;
    }

    @Override
    public Maybe<Object> filter(final Predicate<? super Object> p) {
      return this;
    }

    @Override
    public <B> B fold(final Function<? super Object, B> justCase, final Supplier<B> nothingCase) {
      return nothingCase.get();
    }

    @Override
    public void forEach(final Consumer<? super Object> f) {
    }

    @Override
    public Maybe<Object> orElse(final Supplier<Maybe<? super Object>> alternative) {
      return alternative.get();
    }

    @Override
    public Object getOrElse(final Supplier<Object> alternativeValue) {
      return alternativeValue.get();
    }

    @Override
    public int hashCode() {
      return 0;
    }

    @Override
    public boolean equals(final Object o) {
      return (o == this || o instanceof Nothing);
    }

    @Override
    public String toString() {
      return "Nothing";
    }
  }

  static final class Just<A> extends Maybe<A> {

    private final A value;

    Just(final A value) {
      this.value = value;
    }

    @Override
    public boolean isNothing() {
      return false;
    }

    @Override
    public boolean isJust() {
      return true;
    }

    @Override
    public <B> Maybe<B> map(final Function<? super A, B> f) {
      return Maybe.just(f.apply(value));
    }

    @Override
    public <B> Maybe<B> flatMap(final Function<? super A, Maybe<B>> f) {
      return f.apply(value);
    }

    @Override
    public <B> Maybe<B> ap(final Supplier<Maybe<Function<? super A, B>>> f) {
      return f.get().flatMap(g -> Maybe.just(g.apply(value)));
    }

    @Override
    public Maybe<A> filter(final Predicate<? super A> p) {
      return p.test(value) ? this : Maybe.nothing();
    }

    @Override
    public <B> B fold(final Function<? super A, B> justCase, final Supplier<B> nothingCase) {
      return justCase.apply(value);
    }

    @Override
    public void forEach(final Consumer<? super A> f) {
      f.accept(value);
    }

    @Override
    public Maybe<A> orElse(final Supplier<Maybe<A>> alternative) {
      return this;
    }

    @Override
    public A getOrElse(final Supplier<A> alternativeValue) {
      return this.value;
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(value);
    }

    @Override
    public boolean equals(final Object o) {
      if (o == this) return true;
      if (!(o instanceof Just)) return false;
      final Just<?> that = (Just<?>) o;
      return Objects.equals(this.value, that.value);
    }

    @Override
    public String toString() {
      return "Just(" + String.valueOf(value) + ")";
    }
  }

  static final class NothingHolder {
    private static final Maybe<Object> NOTHING = new Nothing();
  }

  public static <A> Maybe<A> nothing() {
    @SuppressWarnings("unchecked")
    final Maybe<A> nothing = (Maybe<A>) NothingHolder.NOTHING;
    return nothing;
  }

  public static <A> Maybe<A> just(final A value) {
    return value == null ? nothing() : new Just<A>(value);
  }

  public static <A> Maybe<A> fromOptional(final Optional<A> o) {
    return o.map(Maybe::just).orElseGet(Maybe::nothing);
  }

  public static <B> Maybe<B> fromEither(final Either<?, B> e) {
    return e.fold(a -> Maybe.nothing(), Maybe::just);
  }

}
