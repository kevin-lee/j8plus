package j8plus.types;

import testosterone.Testosterone;
import org.junit.jupiter.api.Test;

import static testosterone.Testosterone.test;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-29
 */
public class Predicate3Test {

  @Test
  public void testCurried() throws Exception {
    /* given */
    final Predicate3<Integer, Integer, Integer> allPositives =
        (t1, t2, t3) ->
            t1 > 0 && t2 > 0 && t3 > 0;
    final Testosterone test = test("Predicate3.curried", "curried should return Predicate2 and t1 is already set in the predicate");
    /* @formatter:off */
    test
    .when(() ->
      allPositives.curried(1)
    )
    .then(actual ->
      assertThat(actual.test(1, 1)).isTrue()
    );

    test.when(() ->
      allPositives.curried(-1)
    )
    .then(actual ->
      assertThat(actual.test(1, 1)).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public void testAnd() throws Exception {
    /* Given */
    final Predicate3<Integer, Integer, Integer> allPositives =
        (t1, t2, t3) ->
            t1 > 0 && t2 > 0 && t3 > 0;
    final Predicate3<Integer, Integer, Integer> allGreaterThan5 =
        (t1, t2, t3) ->
            t1 > 5 && t2 > 5 && t3 > 5;

    final Testosterone test = test("Predicate3.and", "Predicate3.and(Predicate3) should check if tests for both predicates return true.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.and(allGreaterThan5)
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 10)).isTrue()
      );

    test
      .when(() ->
        allPositives.and(allGreaterThan5)
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 5)).isFalse()
      );
    /* @formatter:on */

  }

  @Test
  public void testNegate() throws Exception {
    /* Given */
    final Predicate3<Integer, Integer, Integer> allPositives =
        (t1, t2, t3) ->
            t1 > 0 && t2 > 0 && t3 > 0;

    final Testosterone test = test("Predicate3.and", "Predicate3.negate() should return Predicate2 which does opposite to what the original Predicate3 does.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.negate()
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 0)).isTrue()
      );

    test
      .when(() ->
        allPositives.negate()
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 10)).isFalse()
      );
    /* @formatter:on */

  }

  @Test
  public void testOr() throws Exception {
    /* Given */
    final Predicate3<Integer, Integer, Integer> allPositives =
        (t1, t2, t3) ->
            t1 > 0 && t2 > 0 && t3 > 0;
    final Predicate3<Integer, Integer, Integer> hasAnyFive =
        (t1, t2, t3) ->
            t1.equals(5) || t2.equals(5) || t3.equals(5);

    final Testosterone test = test("Predicate3.and", "Predicate3.and(Predicate3) should check if tests for both predicates return true.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 10)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(10, 10, 5)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(-1, -1, 5)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(-1, -1, 10)).isFalse()
      );
    /* @formatter:on */

  }
}
