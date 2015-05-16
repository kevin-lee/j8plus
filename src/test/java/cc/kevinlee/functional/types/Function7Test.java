package cc.kevinlee.functional.types;

import org.junit.Test;

import java.util.function.Function;

import static cc.kevinlee.testosterone.Testosterone.test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function7Test {

  @Test
  public void testAndThen() throws Exception {
    final Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> first = (i1, i2, i3, i4, i5, i6, i7) -> i1 + i2 + i3 + i4 + i5 + i6 + i7;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    final int input7 = 7;
    test("Function7.andThen(Function)", "f7.andThen(f) should apply f7.apply(p1, p2, p3, p4, p5, p6, p7) then f.apply(f7Result). \n" +
        "In other words, f7.andThen(f) == f(f7(p1, p2, p3, p4, p5, p6, p7))")
      .when(() ->
              first.andThen(second).apply(input1, input2, input3, input4, input5, input6, input7)
      )
      .then(actual ->
              assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3, input4, input5, input6, input7)))
      );
  }
}