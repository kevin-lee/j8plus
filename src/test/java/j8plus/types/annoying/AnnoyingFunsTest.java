package j8plus.types.annoying;

import j8plus.types.Runner;
import org.junit.jupiter.api.Test;

import java.util.function.*;

import static j8plus.types.annoying.AnnoyingFuns.*;

import static testosterone.Testosterone.*;
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
            runSomething(AnnoyingFuns.shh(() -> { actual[0] = true; }))
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


  private String getWithAnnoyance() throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <T> T processSupplier(final Supplier<T> supplier) {
    return supplier.get();
  }

  @Test
  public void testShhForAnnoyingSupplier() throws Exception {

    test("testShhForAnnoyingSupplier", "testShh with a method reference to AnnoyingSupplier throwing checked Exception")
        .when(() ->
            processSupplier(shh(this::getWithAnnoyance))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingSupplier (2)", "testShh with a lambda expression for AnnoyingSupplier throwing checked Exception")
        .when(() ->
            processSupplier(shh(() -> getWithAnnoyance()))
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    final String expected = "Whatever";
    test("testShhForAnnoyingSupplier (3)", "testShh with a lambda expression for AnnoyingSupplier throwing no exception")
        .when(() -> {
          AnnoyingSupplier<String> f = () -> expected;
          return processSupplier(shh(f));
        })
        .then(actual ->
            assertThat(actual).isEqualTo(expected)
        );
  }


  private <T> void runWithAnnoyance(final T t) throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <T> void processConsumer(final Consumer<T> consumer, T value) {
    consumer.accept(value);
  }


  @Test
  public void testShhForAnnoyingConsumer() throws Exception {

    test("testShhForAnnoyingConsumer", "testShh with a method reference to AnnoyingConsumer throwing checked Exception")
        .when(() ->
          processConsumer(consumer.shh(this::runWithAnnoyance), null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhForAnnoyingConsumer (2)", "testShh with a lambda expression for AnnoyingConsumer throwing checked Exception")
        .when(() ->
          this.processConsumer(consumer.shh(t -> runWithAnnoyance(t)), null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    final String expected = "Whatever";
    final String[] valueAfterConsumer = new String[1];
    test("testShhForAnnoyingConsumer (3)", "testShh with a lambda expression for AnnoyingConsumer throwing no exception")
        .when(() -> {
          final AnnoyingConsumer<String> f = x -> { valueAfterConsumer[0] = x; };
          this.processConsumer(consumer.shh(f), expected);
        })
        .then(() ->
            assertThat(valueAfterConsumer[0]).isEqualTo(expected)
        );
  }


  private <T> boolean checkWithAnnoyance(final T t) throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <T> boolean processPredicate(final Predicate<T> predicate, T value) {
    return predicate.test(value);
  }

  private <T, U> boolean checkWithAnnoyance2(final T t, U u) throws Exception {
    throw new Exception("Annoying exception!");
  }

  private <T, U> boolean processPredicate2(final BiPredicate<T, U> biPredicate, T value1, U value2) {
    return biPredicate.test(value1, value2);
  }

  @Test
  public void testShhForAnnoyingPredicate() throws Exception {

    test("testShhForAnnoyingPredicate", "testShh with a method reference to AnnoyingPredicate throwing checked Exception")
        .when(() ->
          processPredicate(predicate.shh(this::checkWithAnnoyance), null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhAnnoyingPredicate (2)", "testShh with a lambda expression for AnnoyingPredicate throwing checked Exception")
        .when(() ->
          this.processPredicate(predicate.shh(t -> checkWithAnnoyance(t)), null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhAnnoyingPredicate (3)", "testShh with a lambda expression for AnnoyingConsumer throwing no exception")
        .when(() -> {
          final AnnoyingPredicate<Integer> f = x -> x > 0;
          return this.processPredicate(predicate.shh(f), 1);
        })
        .then(actual ->
            assertThat(actual).isTrue()
        );

    test("testShhAnnoyingPredicate (4)", "testShh with a lambda expression for AnnoyingConsumer throwing no exception")
        .when(() -> {
          return this.processPredicate(predicate.shh(x -> x > 0), 0);
        })
        .then(actual ->
            assertThat(actual).isFalse()
        );
  }

  @Test
  public void testShhForAnnoyingBiPredicate() throws Exception {

    test("testShhForAnnoyingBiPredicate", "testShh with a method reference to AnnoyingBiPredicate throwing checked Exception")
        .when(() ->
          processPredicate2(predicate.shh(this::checkWithAnnoyance2), null, null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhAnnoyingBiPredicate (2)", "testShh with a lambda expression for AnnoyingBiPredicate throwing checked Exception")
        .when(() ->
          this.processPredicate2(predicate.shh((t, u) -> checkWithAnnoyance2(t, u)), null, null)
        )
        .expect(
            throwing(RuntimeException.class)
                .causedBy(Exception.class)
                .hasMessage("Annoying exception!")
        );

    test("testShhAnnoyingBiPredicate (3)", "testShh with a lambda expression for AnnoyingBiPredicate throwing no exception")
        .when(() -> {
          final AnnoyingBiPredicate<Integer, Integer> f = (x, y) -> x.intValue() == y.intValue();
          return this.processPredicate2(predicate.shh(f), 1, 1);
        })
        .then(actual ->
            assertThat(actual).isTrue()
        );

    test("testShhAnnoyingBiPredicate (4)", "testShh with a lambda expression for AnnoyingBiPredicate throwing no exception")
        .when(() -> {
          return this.processPredicate2(predicate.shh((x, y) -> x.intValue() == y.intValue()), 0, 1);
        })
        .then(actual ->
            assertThat(actual).isFalse()
        );
  }

}