package cc.kevinlee.functional.types;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static kevinlee.testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function4Test {

  @Test
  public void testCurried() {
    /* given */
    final Function4<Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4) -> t1 + t2 + t3 + t4;
    test("Function4.curried", "curried should return Function3 and t1 is already set in the function")
        .when(() ->
                function.curried(1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000)).isEqualTo(1_111)
        );
  }

  @Test
  public void testAndThen() throws Exception {
    final Function4<Integer, Integer, Integer, Integer, Integer> first = (i1, i2, i3, i4) -> i1 + i2 + i3 + i4;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    test("Function4.andThen(Function)", "f4.andThen(f) should apply f4.apply(p1, p2, p3, p4) then f.apply(f4Result). \n" +
        "In other words, f4.andThen(f) == f(f4(p1, p2, p3, p4))")
      .when(() ->
        first.andThen(second).apply(input1, input2, input3, input4)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3, input4)))
      );
  }
}