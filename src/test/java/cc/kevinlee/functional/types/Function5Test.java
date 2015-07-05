package cc.kevinlee.functional.types;

import org.junit.Test;

import java.util.function.Function;

import static cc.kevinlee.testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function5Test {

  @Test
  public void testCurried() {
    /* given */
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5) -> t1 + t2 + t3 + t4 + t5;
    test("Function5.curried", "curried should return Function4 and t1 is already set in the function")
        .when(() ->
                function.curried(1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000)).isEqualTo(11_111)
        );
  }

  @Test
  public void testAndThen() throws Exception {
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> first = (i1, i2, i3, i4, i5) -> i1 + i2 + i3 + i4 + i5;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    test("Function5.andThen(Function)", "f5.andThen(f) should apply f5.apply(p1, p2, p3, p4, p5) then f.apply(f5Result). \n" +
        "In other words, f5.andThen(f) == f(f5(p1, p2, p3, p4, p5))")
        .when(() ->
                first.andThen(second).apply(input1, input2, input3, input4, input5)
        )
        .then(actual ->
                assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3, input4, input5)))
        );
  }
}