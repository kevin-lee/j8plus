package j8plus.types.annoying;

/**
 * @author Kevin Lee
 * @since 2017-06-24
 */
@FunctionalInterface
public interface AnnoyingSupplier<T> {
  /**
   * Gets a result.
   *
   * @return a result
   */
  T get() throws Exception;
}
