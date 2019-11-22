package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static testosterone.Testosterone.test;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-07-05
 */
public class Consumer2Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 11;
    final ContainerStoringOnlyOnce<Integer> actual = ContainerStoringOnlyOnce.containerStoringOnlyOnce();
    final Consumer2<Integer, Integer> consumer =
        (t1, t2) -> actual.store(t1 + t2);
    /* @formatter:off */
    test("Consumer2.curried", "curried should return Consumer and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer2<Integer, Integer> first =
        (i1, i2) -> resultPair.value1(i1 + i2);
    final Consumer2<Integer, Integer> second =
        (i1, i2) -> resultPair.value2("Values are " + Arrays.asList(i1, i2).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final List<Integer> inputList = Arrays.asList(input1, input2);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer2.andThen(Consumer2)", "c2.andThen(c2_2) should do c2.accept(p1, p2) then c2_2.accept(p1, p2). \n" +
         "In other words, c2.andThen(c2_2) == c2(p1, p2); c2_2(p1, p2)")
    .when(() ->
      first.andThen(second).accept(input1, input2)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}