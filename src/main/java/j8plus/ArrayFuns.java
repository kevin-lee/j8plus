package j8plus;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-25
 */
public class ArrayFuns {
  private ArrayFuns() {
  }

  /**
   * Checks if the given array is null or empty (length == 0)
   *
   * @param array the given Array of T
   * @param <T>   The type of element stored in the array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static <T> boolean isEmpty(final T[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Checks if the given array is neither null nor empty (must be != null AND length != 0)
   *
   * @param array the given Array of T
   * @param <T>   The type of element stored in the array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static <T> boolean isNotEmpty(final T[] array) {
    return !isEmpty(array);
  }


  /**
   * Checks if the given array is null or empty (length == 0)
   *
   * @param array the given int Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isEmpty(final int[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Checks if the given array is neither null nor empty (must be != null AND length != 0)
   *
   * @param array the given int Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isNotEmpty(final int[] array) {
    return !isEmpty(array);
  }

  /**
   * Checks if the given array is null or empty (length == 0)
   *
   * @param array the given long Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isEmpty(final long[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Checks if the given array is neither null nor empty (must be != null AND length != 0)
   *
   * @param array the given long Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isNotEmpty(final long[] array) {
    return !isEmpty(array);
  }

  /**
   * Checks if the given array is null or empty (length == 0)
   *
   * @param array the given double Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isEmpty(final double[] array) {
    return array == null || array.length == 0;
  }

  /**
   * Checks if the given array is neither null nor empty (must be != null AND length != 0)
   *
   * @param array the given double Array
   * @return true if it is null or empty. Otherwise, false.
   */
  public static boolean isNotEmpty(final double[] array) {
    return !isEmpty(array);
  }

}
