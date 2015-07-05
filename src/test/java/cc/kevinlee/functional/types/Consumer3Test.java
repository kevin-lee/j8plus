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
public class Consumer3Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 111;
    final ContainerStoringOnlyOnce<Integer> actual = containerStoringOnlyOnce();
    final Consumer3<Integer, Integer, Integer> consumer =
        (t1, t2, t3) -> actual.store(t1 + t2 + t3);
    /* @formatter:off */
    test("Consumer3.curried", "curried should return Consumer2 and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10, 100);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer3<Integer, Integer, Integer> first =
        (i1, i2, i3) -> resultPair.value1(i1 + i2 + i3);
    final Consumer3<Integer, Integer, Integer> second =
        (i1, i2, i3) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer3.andThen(Consumer3)", "c3.andThen(c3_2) should do c3.accept(p1, p2, p3) then c3_2.accept(p1, p2, p3). \n" +
         "In other words, c3.andThen(c3_2) == c3(p1, p2, p3); c3_2(p1, p2, p3)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}