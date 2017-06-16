package cc.kevinlee.functional.types.annoying;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Kevin Lee
 * @since 2017-06-10
 */
public class AnnoyingRunnerTest {

  interface ResultChecker {
    void result1();
    void result2();
  }

  @Test
  public void testAndThen() throws Exception {
    final ResultChecker resultChecker = mock(ResultChecker.class);

    final AnnoyingRunner f = () -> resultChecker.result1();
    final AnnoyingRunner g = f.andThen(() -> resultChecker.result2());

    g.run();

    verify(resultChecker).result1();
    verify(resultChecker).result2();
  }

  @Test
  public void testAndThenWithMethodReference() throws Exception {
    final ResultChecker resultChecker = mock(ResultChecker.class);

    final AnnoyingRunner f = resultChecker::result1;
    final AnnoyingRunner g = f.andThen(resultChecker::result2);

    g.run();

    verify(resultChecker).result1();
    verify(resultChecker).result2();
  }

}