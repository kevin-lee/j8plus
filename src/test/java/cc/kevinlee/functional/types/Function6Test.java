package cc.kevinlee.functional.types;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static kevinlee.testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function6Test {

  @Test
  public void testCurried() {
    /* given */
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6) -> t1 + t2 + t3 + t4 + t5 + t6;
    test("Function6.curried", "curried should return Function5 and t1 is already set in the function")
        .when(() ->
                function.curried(1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000)).isEqualTo(111_111)
        );
  }

  @Test
  public void testAndThen() throws Exception {
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> first = (i1, i2, i3, i4, i5, i6) -> i1 + i2 + i3 + i4 + i5 + i6;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    test("Function6.andThen(Function)", "f6.andThen(f) should apply f6.apply(p1, p2, p3, p4, p5, p6) then f.apply(f6Result). \n" +
        "In other words, f6.andThen(f) == f(f6(p1, p2, p3, p4, p5, p6))")
        .when(() ->
                first.andThen(second).apply(input1, input2, input3, input4, input5, input6)
        )
        .then(actual ->
                assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3, input4, input5, input6)))
        );
  }
}