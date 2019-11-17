package j8plus.types.annoying;

/**
 * This is a FunctionalInterface to handle () -> void case
 * which may or may not throw a checked exception.
 *
 * @author Kevin Lee
 * @since 2019-11-16
 */
@FunctionalInterface
public interface AnnoyingConsumer<T> {

  void accept(T t) throws Exception;

}
