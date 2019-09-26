package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static kevinlee.testosterone.Testosterone.test;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public class Consumer6Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 111_111;
    final ContainerStoringOnlyOnce<Integer> actual = ContainerStoringOnlyOnce.containerStoringOnlyOnce();
    final Consumer6<Integer, Integer, Integer, Integer, Integer, Integer> consumer =
        (t1, t2, t3, t4, t5, t6) -> actual.store(t1 + t2 + t3 + t4 + t5 + t6);
    /* @formatter:off */
    test("Consumer6.curried", "curried should return Consumer5 and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10, 100, 1_000, 10_000, 100_000);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer6<Integer, Integer, Integer, Integer, Integer, Integer> first =
        (i1, i2, i3, i4, i5, i6) -> resultPair.value1(i1 + i2 + i3 + i4 + i5 + i6);
    final Consumer6<Integer, Integer, Integer, Integer, Integer, Integer> second =
        (i1, i2, i3, i4, i5, i6) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3, i4, i5, i6).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3, input4, input5, input6);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer6.andThen(Consumer6)", "c6.andThen(c6_2) should do c6.accept(p1, p2, p3, p4, p5, p6) then c6_2.accept(p1, p2, p3, p4, p5, p6). \n" +
         "In other words, c6.andThen(c6_2) == c6(p1, p2, p3, p4, p5, p6); c6_2(p1, p2, p3, p4, p5, p6)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3, input4, input5, input6)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}