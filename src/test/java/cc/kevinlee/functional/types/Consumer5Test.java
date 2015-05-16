package cc.kevinlee.functional.types;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static cc.kevinlee.testosterone.Testosterone.test;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public class Consumer5Test implements TypesUtil {

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer5<Integer, Integer, Integer, Integer, Integer> first =
        (i1, i2, i3, i4, i5) -> resultPair.value1(i1 + i2 + i3 + i4 + i5);
    final Consumer5<Integer, Integer, Integer, Integer, Integer> second =
        (i1, i2, i3, i4, i5) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3, i4, i5).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3, input4, input5);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer5.andThen(Consumer5)", "c5.andThen(c5_2) should do c5.accept(p1, p2, p3, p4, p5) then c5_2.accept(p1, p2, p3, p4, p5). \n" +
         "In other words, c5.andThen(c5_2) == c5(p1, p2, p3, p4, p5); c5_2(p1, p2, p3, p4, p5)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3, input4, input5)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}