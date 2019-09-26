package j8plus.types;

import java.util.Objects;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public interface TypesUtil {

  interface Pair<K, V> {
    K value1();
    V value2();
  }

  static class ImmutablePair<K, V> implements Pair<K, V> {
    public final K value1;
    public final V value2;

    public ImmutablePair(final K value1, final V value2) {
      this.value1 = value1;
      this.value2 = value2;
    }

    @Override
    public K value1() {
      return value1;
    }

    @Override
    public V value2() {
      return value2;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      final Pair<?, ?> that = (Pair<?, ?>) o;
      return Objects.equals(value1, that.value1()) &&
          Objects.equals(value2, that.value2());
    }

    @Override
    public int hashCode() {
      return Objects.hash(value1, value2);
    }
  }
  static class MutablePair<K, V> implements Pair<K, V> {
    private K value1;
    private V value2;

    public K value1() {
      return value1;
    }
    public void value1(final K value1) {
      this.value1 = value1;
    }

    public V value2() {
      return value2;
    }
    public void value2(final V value2) {
      this.value2 = value2;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      final Pair<?, ?> that = (Pair<?, ?>) o;
      return Objects.equals(value1, that.value1()) &&
          Objects.equals(value2, that.value2());
    }

    @Override
    public int hashCode() {
      return Objects.hash(value1, value2);
    }
  }

  static class ContainerStoringOnlyOnce<T> {
    private volatile T value;

    private ContainerStoringOnlyOnce() {
    }

    public static <E> ContainerStoringOnlyOnce<E> containerStoringOnlyOnce() {
      return new ContainerStoringOnlyOnce<E>();
    }

    public T getValue() {
      return value;
    }

    public ContainerStoringOnlyOnce store(T value) {
      T localValue = this.value;

      if (localValue == null) {
        synchronized (this) {
          localValue = this.value;
          if (localValue == null) {
            this.value = localValue = value;
            return this;
          }
        }
      }
      throw new IllegalStateException("You tried to store the value, " + value +
          ", into this ContainerStoringOnlyOnce when it already has the value that is " + this.value + ". \n" +
          "You cannot reuse this ContainerStoringOnlyOnce and store the new value.");
    }
  }
}
