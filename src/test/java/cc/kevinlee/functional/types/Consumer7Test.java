package cc.kevinlee.functional.types;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static cc.kevinlee.functional.types.TypesUtil.ContainerStoringOnlyOnce.containerStoringOnlyOnce;
import static cc.kevinlee.testosterone.Testosterone.test;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public class Consumer7Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 1_111_111;
    final ContainerStoringOnlyOnce<Integer> actual = containerStoringOnlyOnce();
    final Consumer7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> consumer =
        (t1, t2, t3, t4, t5, t6, t7) -> actual.store(t1 + t2 + t3 + t4 + t5 + t6 + t7);
    /* @formatter:off */
    test("Consumer7.curried", "curried should return Consumer6 and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> first =
        (i1, i2, i3, i4, i5, i6, i7) -> resultPair.value1(i1 + i2 + i3 + i4 + i5 + i6 + i7);
    final Consumer7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> second =
        (i1, i2, i3, i4, i5, i6, i7) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3, i4, i5, i6, i7).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    final int input7 = 7;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3, input4, input5, input6, input7);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer7.andThen(Consumer7)", "c7.andThen(c7_2) should do c7.accept(p1, p2, p3, p4, p5, p6, p7) then c7_2.accept(p1, p2, p3, p4, p5, p6, p7). \n" +
         "In other words, c7.andThen(c7_2) == c7(p1, p2, p3, p4, p5, p6, p7); c7_2(p1, p2, p3, p4, p5, p6, p7)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3, input4, input5, input6, input7)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}