package j8plus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

import static org.assertj.core.api.Assertions.*;
import static kevinlee.testosterone.Testosterone.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-25
 */
public class ArrayFunsTest {

  @Test
  public void testIsEmpty() throws Exception {
    final String[] array = {};
    test("Test ArrayFunc.isEmpty(T[]) with an empty array", "ArrayFunc.isEmpty(emptyArray) should return true")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsEmpty2() throws Exception {
    final String[] array = {"something", "something else"};
    test("Test ArrayFunc.isEmpty(T[]) with a non empty array", "ArrayFunc.isEmpty(notEmptyArray) should return false")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsEmptyForStream() throws Exception {
    final String[] array = {"something", "something else"};
    test("Test ArrayFunc.isEmpty(T[]) for stream with a non empty array", "ArrayFunc::isEmpty for stream with a non empty array")
        .when(() ->
                Arrays.<String[]>asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForStream2() throws Exception {
    final String[] array = {};
    test("Test ArrayFunc.isEmpty(T[]) for stream with an empty array", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.<String[]>asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsEmptyForStream3() throws Exception {
    final String[] array = {};
    final String[] array2 = {};
    test("Test ArrayFunc.isEmpty(T[])", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.<String[]>asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsNotEmpty() throws Exception {
    final String[] array = {};
    test("Test ArrayFunc.isNotEmpty(T[]) with an empty array", "ArrayFunc.isNotEmpty(emptyArray) should return false")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsNotEmpty2() throws Exception {
    final String[] array = {"something", "something else"};
    test("Test ArrayFunc.isNotEmpty(T[]) with a non empty array", "ArrayFunc.isNotEmpty(notEmptyArray) should return true")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsNotEmptyForStream() throws Exception {
    final String[] array = {"something", "something else"};
    test("Test ArrayFunc.isNotEmpty(T[]) for stream with a non empty array", "ArrayFunc::isNotEmpty for stream with a non empty array")
        .when(() ->
                Arrays.<String[]>asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testisNotEmptyForStream2() throws Exception {
    final String[] array = {};
    test("Test ArrayFunc.isNotEmpty(T[]) for stream with an empty array", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.<String[]>asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testisNotEmptyForStream3() throws Exception {
    final String[] array = {};
    final String[] array2 = {};
    test("Test ArrayFunc.isNotEmpty(T[])", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.<String[]>asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForIntArray() throws Exception {
    final int[] array = {};
    test("Test ArrayFunc.isEmpty(int[]) with an empty array", "ArrayFunc.isEmpty(emptyArray) should return true")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsEmptyForIntArray2() throws Exception {
    final int[] array = {1, 2, 3};
    test("Test ArrayFunc.isEmpty(int[]) with a non empty array", "ArrayFunc.isEmpty(notEmptyArray) should return false")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsEmptyForIntArrayForStream() throws Exception {
    final int[] array = {1, 2, 3};
    test("Test ArrayFunc.isEmpty(int[]) for stream with a non empty array", "ArrayFunc::isEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForIntArrayForStream2() throws Exception {
    final int[] array = {};
    test("Test ArrayFunc.isEmpty(int[]) for stream with an empty array", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsEmptyForIntArrayForStream3() throws Exception {
    final int[] array = {};
    final int[] array2 = {};
    test("Test ArrayFunc.isEmpty(int[])", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsNotEmptyForIntArray() throws Exception {
    final int[] array = {};
    test("Test ArrayFunc.isNotEmpty(int[]) with an empty array", "ArrayFunc.isNotEmpty(emptyArray) should return false")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsNotEmptyForIntArray2() throws Exception {
    final int[] array = {1, 2, 3};
    test("Test ArrayFunc.isNotEmpty(int[]) with a non empty array", "ArrayFunc.isNotEmpty(notEmptyArray) should return true")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsNotEmptyForIntArrayForStream() throws Exception {
    final int[] array = {1, 2, 3};
    test("Test ArrayFunc.isNotEmpty(int[]) for stream with a non empty array", "ArrayFunc::isNotEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testisNotEmptyForIntArrayForStream2() throws Exception {
    final int[] array = {};
    test("Test ArrayFunc.isNotEmpty(int[]) for stream with an empty array", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testisNotEmptyForIntArrayForStream3() throws Exception {
    final int[] array = {};
    final int[] array2 = {};
    test("Test ArrayFunc.isNotEmpty(int[])", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForLongArray() throws Exception {
    final long[] array = {};
    test("Test ArrayFunc.isEmpty(long[]) with an empty array", "ArrayFunc.isEmpty(emptyArray) should return true")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsEmptyForLongArray2() throws Exception {
    final long[] array = {1, 2, 3};
    test("Test ArrayFunc.isEmpty(long[]) with a non empty array", "ArrayFunc.isEmpty(notEmptyArray) should return false")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsEmptyForLongArrayForStream() throws Exception {
    final long[] array = {1, 2, 3};
    test("Test ArrayFunc.isEmpty(long[]) for stream with a non empty array", "ArrayFunc::isEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForLongArrayForStream2() throws Exception {
    final long[] array = {};
    test("Test ArrayFunc.isEmpty(long[]) for stream with an empty array", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsEmptyForLongArrayForStream3() throws Exception {
    final long[] array = {};
    final long[] array2 = {};
    test("Test ArrayFunc.isEmpty(long[])", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsNotEmptyForLongArray() throws Exception {
    final long[] array = {};
    test("Test ArrayFunc.isNotEmpty(long[]) with an empty array", "ArrayFunc.isNotEmpty(emptyArray) should return false")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsNotEmptyForLongArray2() throws Exception {
    final long[] array = {1, 2, 3};
    test("Test ArrayFunc.isNotEmpty(long[]) with a non empty array", "ArrayFunc.isNotEmpty(notEmptyArray) should return true")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsNotEmptyForLongArrayForStream() throws Exception {
    final long[] array = {1, 2, 3};
    test("Test ArrayFunc.isNotEmpty(long[]) for stream with a non empty array", "ArrayFunc::isNotEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testisNotEmptyForLongArrayForStream2() throws Exception {
    final long[] array = {};
    test("Test ArrayFunc.isNotEmpty(long[]) for stream with an empty array", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testisNotEmptyForLongArrayForStream3() throws Exception {
    final long[] array = {};
    final long[] array2 = {};
    test("Test ArrayFunc.isNotEmpty(long[])", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForDoubleArray() throws Exception {
    final double[] array = {};
    test("Test ArrayFunc.isEmpty(double[]) with an empty array", "ArrayFunc.isEmpty(emptyArray) should return true")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsEmptyForDoubleArray2() throws Exception {
    final double[] array = {1.0D, 2.2D, 3.5D};
    test("Test ArrayFunc.isEmpty(double[]) with a non empty array", "ArrayFunc.isEmpty(notEmptyArray) should return false")
        .when(() ->
                ArrayFuns.isEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsEmptyForDoubleArrayForStream() throws Exception {
    final double[] array = {1.0D, 2.2D, 3.5D};
    test("Test ArrayFunc.isEmpty(double[]) for stream with a non empty array", "ArrayFunc::isEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testIsEmptyForDoubleArrayForStream2() throws Exception {
    final double[] array = {};
    test("Test ArrayFunc.isEmpty(double[]) for stream with an empty array", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsEmptyForDoubleArrayForStream3() throws Exception {
    final double[] array = {};
    final double[] array2 = {};
    test("Test ArrayFunc.isEmpty(double[])", "ArrayFunc::isEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testIsNotEmptyForDoubleArray() throws Exception {
    final double[] array = {};
    test("Test ArrayFunc.isNotEmpty(double[]) with an empty array", "ArrayFunc.isNotEmpty(emptyArray) should return false")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isFalse()
    );
  }

  @Test
  public void testIsNotEmptyForDoubleArray2() throws Exception {
    final double[] array = {1.0D, 2.2D, 3.5D};
    test("Test ArrayFunc.isNotEmpty(double[]) with a non empty array", "ArrayFunc.isNotEmpty(notEmptyArray) should return true")
        .when(() ->
                ArrayFuns.isNotEmpty(array)
        ).then(actual ->
            assertThat(actual).isTrue()
    );
  }

  @Test
  public void testIsNotEmptyForDoubleArrayForStream() throws Exception {
    final double[] array = {1.0D, 2.2D, 3.5D};
    test("Test ArrayFunc.isNotEmpty(double[]) for stream with a non empty array", "ArrayFunc::isNotEmpty for stream with a non empty array")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isNotEmpty()
    );
  }

  @Test
  public void testisNotEmptyForDoubleArrayForStream2() throws Exception {
    final double[] array = {};
    test("Test ArrayFunc.isNotEmpty(double[]) for stream with an empty array", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

  @Test
  public void testisNotEmptyForDoubleArrayForStream3() throws Exception {
    final double[] array = {};
    final double[] array2 = {};
    test("Test ArrayFunc.isNotEmpty(double[])", "ArrayFunc::isNotEmpty for stream")
        .when(() ->
                Arrays.asList(array, array2)
                    .stream()
                    .filter(ArrayFuns::isNotEmpty)
                    .collect(toList())
        ).then(actual ->
            assertThat(actual).isEmpty()
    );
  }

}