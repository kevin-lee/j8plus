package cc.kevinlee.functional.types;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static cc.kevinlee.functional.types.TypesUtil.ContainerStoringOnlyOnce.containerStoringOnlyOnce;
import static kevinlee.testosterone.Testosterone.test;
import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public class Consumer4Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 1_111;
    final ContainerStoringOnlyOnce<Integer> actual = containerStoringOnlyOnce();
    final Consumer4<Integer, Integer, Integer, Integer> consumer =
        (t1, t2, t3, t4) -> actual.store(t1 + t2 + t3 + t4);
    /* @formatter:off */
    test("Consumer4.curried", "curried should return Consumer3 and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10, 100, 1_000);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer4<Integer, Integer, Integer, Integer> first =
        (i1, i2, i3, i4) -> resultPair.value1(i1 + i2 + i3 + i4);
    final Consumer4<Integer, Integer, Integer, Integer> second =
        (i1, i2, i3, i4) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3, i4).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3, input4);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer4.andThen(Consumer4)", "c4.andThen(c4_2) should do c4.accept(p1, p2, p3, p4) then c4_2.accept(p1, p2, p3, p4). \n" +
         "In other words, c4.andThen(c4_2) == c4(p1, p2, p3, p4); c4_2(p1, p2, p3, p4)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3, input4)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}