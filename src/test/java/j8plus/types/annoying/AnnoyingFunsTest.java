package j8plus.types.annoying;

import j8plus.types.Runner;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static j8plus.types.annoying.AnnoyingFuns.shh;

import static kevinlee.testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-24
 */
public class AnnoyingFunsTest {

  private <E, T> T doItWithAnnoyance(E whatEver) throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <E, T> T doSomething(Function<E, T> function) {
    return function.apply(null);
  }

  @Test
  public void testShhForAnnoyingFunction() throws Exception {

    test("testShhForAnnoyingFunction", "testShh with a method reference throwing checked Exception")
        .when(() ->
          doSomething(AnnoyingFuns.shh(this::doItWithAnnoyance))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingFunction (2)", "testShh with a lambda expression throwing checked Exception")
        .when(() ->
          doSomething(AnnoyingFuns.shh(x -> doItWithAnnoyance(x)))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    final String expected = "OK";
    final AnnoyingFunction<Object, String> functionWithoutThrowingException = (x) -> expected;
    test("testShhForAnnoyingFunction (3)", "testShh with a lambda expression throwing no exception")
        .when(() ->
                doSomething(AnnoyingFuns.shh(functionWithoutThrowingException))
        )
        .then(actual ->
                assertThat(actual).isEqualTo(expected)
        );
  }

  private <T, U, R> R doItWithAnnoyance2(T input1, U input2) throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <T, U, R> R doSomething(BiFunction<T, U, R> function) {
    return function.apply(null, null);
  }

  @Test
  public void testShhForAnnoyingBiFunction() throws Exception {
    test("testShhForAnnoyingBiFunction", "testShh with a method reference throwing checked Exception")
        .when(() ->
          doSomething(AnnoyingFuns.shh(this::doItWithAnnoyance2))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingBiFunction (2)", "testShh with a lambda expression throwing checked Exception")
        .when(() ->
          doSomething(AnnoyingFuns.shh((x, y) -> doItWithAnnoyance2(x, y)))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    final String expected = "OK";
    final AnnoyingBiFunction<Object, Object, String> biFunctionWithoutThrowingException = (x, y) -> expected;
    test("testShhForAnnoyingBiFunction (3)", "testShh with a lambda expression throwing no exception")
        .when(() ->
          doSomething(AnnoyingFuns.shh(biFunctionWithoutThrowingException))
        )
        .then(actual ->
          assertThat(actual).isEqualTo(expected)
        );
  }


  private void runItWithAnnoyance() throws Exception {
    throw new Exception("Annoying exception!");
  }


  private void runSomething(final Runnable runnable) {
    runnable.run();
  }




  @Test
  public void testShhForAnnoyingRunnable() throws Exception {

    test("testShhForAnnoyingRunnable", "testShh with a method reference to AnnoyingRunnable throwing checked Exception")
        .when(() ->
            runSomething(AnnoyingFuns.shh(this::runItWithAnnoyance))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingRunnable (2)", "testShh with a lambda expression for AnnoyingRunnable throwing checked Exception")
        .when(() ->
            runSomething(AnnoyingFuns.shh(() -> runItWithAnnoyance()))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    final boolean expected = true;
    final boolean[] actual = {false};
    test("testShhForAnnoyingRunnable (3)", "testShh with a lambda expression for AnnoyingRunnable throwing no exception")
        .when(() ->
            runSomething(AnnoyingFuns.shh(() -> actual[0] = true))
        )
        .then(() ->
            assertThat(actual[0]).isEqualTo(expected)
        );
  }



  private void runWithRunner(final Runner runnable) {
    runnable.run();
  }



  @Test
  public void testShhForAnnoyingRunner() throws Exception {

    test("testShhForAnnoyingRunner", "testShh with a method reference to AnnoyingRunner throwing checked Exception")
        .when(() ->
            runWithRunner(AnnoyingFuns.shh(this::runItWithAnnoyance))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingRunner (2)", "testShh with a lambda expression for AnnoyingRunner throwing checked Exception")
        .when(() ->
            runWithRunner(AnnoyingFuns.shh(() -> runItWithAnnoyance()))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    final boolean expected = true;
    final boolean[] actual = { false };
    test("testShhForAnnoyingRunner (3)", "testShh with a lambda expression for AnnoyingRunner throwing no exception")
        .when(() -> {
          AnnoyingRunner f = () -> actual[0] = true;
          runWithRunner(AnnoyingFuns.shh(f));
        })
        .then(() ->
            assertThat(actual[0]).isEqualTo(expected)
        );
  }

}