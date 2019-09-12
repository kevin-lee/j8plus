package cc.kevinlee.functional.types;

import kevinlee.testosterone.Testosterone;
import org.junit.jupiter.api.Test;

import static kevinlee.testosterone.Testosterone.test;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-29
 */
public class Predicate2Test {

  @Test
  public void testCurried() throws Exception {
    /* given */
    final Predicate2<Integer, Integer> allPositives =
        (t1, t2) ->
            t1 > 0 && t2 > 0;
    final Testosterone test = test("Predicate2.curried", "curried should return Predicate and t1 is already set in the predicate");
    /* @formatter:off */
    test
    .when(() ->
      allPositives.curried(1)
    )
    .then(actual ->
      assertThat(actual.test(1)).isTrue()
    );

    test.when(() ->
      allPositives.curried(-1)
    )
    .then(actual ->
      assertThat(actual.test(1)).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public void testAnd() throws Exception {
    /* Given */
    final Predicate2<Integer, Integer> allPositives =
        (t1, t2) ->
            t1 > 0 && t2 > 0;
    final Predicate2<Integer, Integer> allGreaterThan5 =
        (t1, t2) ->
            t1 > 5 && t2 > 5;

    final Testosterone test = test("Predicate2.and", "Predicate2.and(Predicate2) should check if tests for both predicates return true.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.and(allGreaterThan5)
      )
      .then(actual ->
        assertThat(actual.test(10, 10)).isTrue()
      );

    test
      .when(() ->
        allPositives.and(allGreaterThan5)
      )
      .then(actual ->
        assertThat(actual.test(10, 5)).isFalse()
      );
    /* @formatter:on */

  }

  @Test
  public void testNegate() throws Exception {
    /* Given */
    final Predicate2<Integer, Integer> allPositives =
        (t1, t2) ->
            t1 > 0 && t2 > 0;

    final Testosterone test = test("Predicate2.and", "Predicate2.negate() should return Predicate which does opposite to what the original Predicate2 does.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.negate()
      )
      .then(actual ->
        assertThat(actual.test(10, 0)).isTrue()
      );

    test
      .when(() ->
        allPositives.negate()
      )
      .then(actual ->
        assertThat(actual.test(10, 10)).isFalse()
      );
    /* @formatter:on */

  }

  @Test
  public void testOr() throws Exception {
    /* Given */
    final Predicate2<Integer, Integer> allPositives =
        (t1, t2) ->
            t1 > 0 && t2 > 0;
    final Predicate2<Integer, Integer> hasAnyFive =
        (t1, t2) ->
            t1.equals(5) || t2.equals(5);

    final Testosterone test = test("Predicate2.and", "Predicate2.and(Predicate2) should check if tests for both predicates return true.");
    /* @formatter:off */
    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(10, 10)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(10, 5)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(-1, 5)).isTrue()
      );

    test
      .when(() ->
        allPositives.or(hasAnyFive)
      )
      .then(actual ->
        assertThat(actual.test(-1, 10)).isFalse()
      );
    /* @formatter:on */

  }
}
