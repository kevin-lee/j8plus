package cc.kevinlee.functional.types;

import kevinlee.testosterone.Testosterone;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static kevinlee.testosterone.Testosterone.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static cc.kevinlee.functional.types.TypesUtil.ContainerStoringOnlyOnce.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-17
 */
public class Consumer10Test implements TypesUtil {

  @Test
  public void testCurried() {
    /* given */
    final Integer expected = 1_111_111_111;
    final ContainerStoringOnlyOnce<Integer> actual = containerStoringOnlyOnce();
    final Consumer10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> consumer =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> actual.store(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10);
    /* @formatter:off */
    test("Consumer10.curried", "curried should return Consumer9 and t1 is already set in the consumer")
      .when(() ->
        consumer.curried(1)
      )
      .then(curried -> {
        curried.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000);
        assertThat(actual.getValue()).isEqualTo(expected);
      });
    /* @formatter:on */
  }

  @Test
  public void testAndThen() throws Exception {
    final MutablePair<Integer, String> resultPair = new MutablePair<>();

    final Consumer10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> first =
        (i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) -> resultPair.value1(i1 + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9 + i10);
    final Consumer10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> second =
        (i1, i2, i3, i4, i5, i6, i7, i8, i9, i10) -> resultPair.value2("Values are " + Arrays.asList(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10).stream().map(String::valueOf).collect(joining(", ")));

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    final int input4 = 4;
    final int input5 = 5;
    final int input6 = 6;
    final int input7 = 7;
    final int input8 = 8;
    final int input9 = 9;
    final int input10 = 10;
    final List<Integer> inputList = Arrays.asList(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10);
    final Pair<Integer, String> expected  =
        new ImmutablePair<>(inputList.stream().reduce(0, (prev, i) -> prev + i),
                            "Values are " + inputList.stream().map(String::valueOf).collect(joining(", ")));
    test("Consumer10.andThen(Consumer10)", "c10.andThen(c10_2) should do c10.accept(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) then c10_2.accept(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10). \n" +
         "In other words, c10.andThen(c10_2) == c10(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10); c10_2(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)")
    .when(() ->
      first.andThen(second).accept(input1, input2, input3, input4, input5, input6, input7, input8, input9, input10)
    )
    .then(() ->
      assertThat(resultPair).isEqualTo(expected)
    );
  }
}
