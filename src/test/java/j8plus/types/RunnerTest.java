package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;


/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-13
 */
public class RunnerTest {

  @Test
  public void testAndThen() throws Exception {

    final List<Integer> resultCollector = new ArrayList<>();
    final Runner first = () -> resultCollector.add(1);
    final Runner second = () -> resultCollector.add(2);

    test("Runner.andThen()", "runner1.andThen(runner2) should run runner1.run() then runner2.run()")
        .when(() -> first.andThen(second).run())
        .then(() ->
            assertThat(resultCollector).isEqualTo(Arrays.asList(1, 2))
        );

  }
  @Test
  public void testAndThen2() throws Exception {

    final List<Integer> resultCollector = new ArrayList<>();
    final Runner first = () -> resultCollector.add(1);
    final Runner second = () -> resultCollector.add(2);

    test("Runner.andThen()", "runner1.andThen(runner2) should run runner1.run() then runner2.run()")
        .when(() -> second.andThen(first).run())
        .then(() ->
            assertThat(resultCollector).isEqualTo(Arrays.asList(2, 1))
        );

  }
}
