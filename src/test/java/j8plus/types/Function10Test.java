package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function10Test {

  @Test
  public void testCurried() {
    /* given */
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
    /* @formatter:off */
    test("Function10.curried", "curried should return Function9 and t1 is already set in the function")
      .when(() ->
        function.curried(1)
      )
      .then(actual ->
        assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isEqualTo(1_111_111_111)
      );
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> first = (i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) -> i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9 + i10;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    final int input7 = 7;
    final int input8 = 8;
    final int input9 = 9;
    final int input10 = 10;
    /* @formatter:off */
    test("Function10.andThen(Function)", "f10.andThen(f) should apply f10.apply(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) then f.apply(f10Result). \n" +
         "In other words, f10.andThen(f) == f(f10(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10))")
      .when(() ->
        first.andThen(second).apply(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10)))
      );
    /* @formatter:on */
  }
}