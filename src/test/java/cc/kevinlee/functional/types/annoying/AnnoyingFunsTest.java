package cc.kevinlee.functional.types.annoying;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static cc.kevinlee.functional.types.annoying.AnnoyingFuns.shh;

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
          doSomething(shh(this::doItWithAnnoyance))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingFunction (2)", "testShh with a lambda expression throwing checked Exception")
        .when(() ->
          doSomething(shh(x -> doItWithAnnoyance(x)))
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
                doSomething(shh(functionWithoutThrowingException))
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
          doSomething(shh(this::doItWithAnnoyance2))
        )
        .expect(
          throwing(RuntimeException.class)
            .causedBy(Exception.class)
            .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingBiFunction (2)", "testShh with a lambda expression throwing checked Exception")
        .when(() ->
          doSomething(shh((x, y) -> doItWithAnnoyance2(x, y)))
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
          doSomething(shh(biFunctionWithoutThrowingException))
        )
        .then(actual ->
          assertThat(actual).isEqualTo(expected)
        );
  }
}