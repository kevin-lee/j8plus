package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-22
 */
public class Function2Test {

  @Test
  public void testCurried() {
    /* given */
    final Function2<Integer, Integer, Integer> function =
        (t1, t2) -> t1 + t2;
    test("Function2.curried", "curried should return Function and t1 is already set in the function")
        .when(() ->
                function.curried(1)
        )
        .then(actual ->
                assertThat(actual.apply(10)).isEqualTo(11)
        );
  }

  @Test
  public void testAndThen() throws Exception {

    final Function2<Integer, Integer, Integer> first = (i1, i2) -> i1 + i2;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    test("Function2.andThen(Function)", "f2.andThen(f) should apply f2.apply(p1, p2) then f.apply(f2Result). \n" +
        "In other words, f2.andThen(f) == f(f2(p1, p2))")
        .when(() ->
                first.andThen(second).apply(input1, input2)
        )
        .then(actual ->
                assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2)))
        );
  }
}