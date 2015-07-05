package cc.kevinlee.functional.spicy;

import cc.kevinlee.functional.types.*;
import org.elixirian.kommonlee.test.CommonTestHelper;
import cc.kevinlee.functional.types.TypesUtil.ContainerStoringOnlyOnce;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static cc.kevinlee.functional.spicy.Curry.*;
import static cc.kevinlee.testosterone.Testosterone.*;

import static org.assertj.core.api.Assertions.*;

import static cc.kevinlee.functional.types.TypesUtil.ContainerStoringOnlyOnce.*;


/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-22
 */
public class CurryTest {

  @Test
  public void testCurry() throws Exception {
    /* @formatter:off */
    test("test Curry()", "Curry() must be private")
        .when(() ->
          CommonTestHelper.newConstructorTester(Curry.class, this).mustBePrivate().test()
        )
        .expect(throwing(IllegalAccessException.class));
    /* @formatter:on */
  }

  @Test
  public void testCurrying2() throws Exception {
    /* @formatter:off */
    final BiFunction<Integer, Integer, Integer> biFunction = (t1, t2) -> t1 + t2;
    test("currying(biFunction, t1)", "Test Curry.currying(BiFunction, T1)")
        .when(() ->
          currying(biFunction, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function.class)
        )
        .then(actual ->
          assertThat(actual.apply(10)).isEqualTo(11)
        );

    final Function2<Integer, Integer, Integer> function =
        (t1, t2) -> t1 + t2;
    final Function2<Integer, Integer, Integer> nullFunction = null;
    test("currying(function2, t1)", "Test Curry.currying(Function2, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function.class)
        )
        .then(actual ->
          assertThat(actual.apply(10)).isEqualTo(11)
        );

    test("currying(Function2 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll2(Integer t1, Integer t2) {
    return t1 + t2;
  }

  @Test
  public void testCurryingForFunction2WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(BiFunction, T1)")
        .when(() ->
          currying(CurryTest::addAll2, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function.class)
        )
        .then(actual ->
          assertThat(actual.apply(10)).isEqualTo(11)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying3() throws Exception {
    final Function3<Integer, Integer, Integer, Integer> function = (t1, t2, t3) -> t1 + t2 + t3;
    final Function3<Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function3, t1)", "Test Curry.currying(Function3, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
                assertThat(actual).isInstanceOf(Function2.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100)).isEqualTo(111)
        );

    test("currying(Function3 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll3(Integer t1, Integer t2, Integer t3) {
    return t1 + t2 + t3;
  }

  @Test
  public void testCurryingForFunction3WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function3, T1)")
        .when(() ->
          currying(CurryTest::addAll3, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function2.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100)).isEqualTo(111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying4() throws Exception {
    final Function4<Integer, Integer, Integer, Integer, Integer> function = (t1, t2, t3, t4) -> t1 + t2 + t3 + t4;
    final Function4<Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function4, t1)", "Test Curry.currying(Function4, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function3.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000)).isEqualTo(1_111)
        );

    test("currying(Function4 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll4(Integer t1, Integer t2, Integer t3, Integer t4) {
    return t1 + t2 + t3 + t4;
  }

  @Test
  public void testCurryingForFunction4WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function4, T1)")
        .when(() ->
          currying(CurryTest::addAll4, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function3.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000)).isEqualTo(1_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying5() throws Exception {
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5) -> t1 + t2 + t3 + t4 + t5;
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function5, t1)", "Test Curry.currying(Function5, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function4.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000)).isEqualTo(11_111)
        );

    test("currying(Function5 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll5(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5) {
    return t1 + t2 + t3 + t4 + t5;
  }

  @Test
  public void testCurryingForFunction5WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function5, T1)")
        .when(() ->
          currying(CurryTest::addAll5, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function4.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000)).isEqualTo(11_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying6() throws Exception {
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6) -> t1 + t2 + t3 + t4 + t5 + t6;
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function6, t1)", "Test Curry.currying(Function6, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function5.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000)).isEqualTo(111_111)
        );

    test("currying(Function6 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll6(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
    return t1 + t2 + t3 + t4 + t5 + t6;
  }

  @Test
  public void testCurryingForFunction6WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function6, T1)")
        .when(() ->
          currying(CurryTest::addAll6, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function5.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000)).isEqualTo(111_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying7() throws Exception {
    final Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7) -> t1 + t2 + t3 + t4 + t5 + t6 + t7;
    final Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function7, t1)", "Test Curry.currying(Function7, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function6.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000)).isEqualTo(1_111_111)
        );

    test("currying(Function7 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll7(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7;
  }

  @Test
  public void testCurryingForFunction7WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function7, T1)")
        .when(() ->
          currying(CurryTest::addAll7, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function6.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000)).isEqualTo(1_111_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying8() throws Exception {
    final Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;
    final Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    /* @formatter:off */
    test("currying(function8, t1)", "Test Curry.currying(Function8, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function7.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000)).isEqualTo(11_111_111)
        );

    test("currying(Function8 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll8(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;
  }

  @Test
  public void testCurryingForFunction8WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function8, T1)")
        .when(() ->
          currying(CurryTest::addAll8, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function7.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000)).isEqualTo(11_111_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying9() throws Exception {
    final Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
    final Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function9, t1)", "Test Curry.currying(Function9, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function8.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000)).isEqualTo(111_111_111)
        );

    test("currying(Function9 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll9(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
  }

  @Test
  public void testCurryingForFunction9WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function9, T1)")
        .when(() ->
          currying(CurryTest::addAll9, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function8.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000)).isEqualTo(111_111_111)
        );
    /* @formatter:on */

  }

  @Test
  public void testCurrying10() throws Exception {
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;

    /* @formatter:off */
    test("currying(function10, t1)", "Test Curry.currying(Function10, T1)")
        .when(() ->
          currying(function, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function9.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isEqualTo(1_111_111_111)
        );

    test("currying(Function10 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
          currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
    /* @formatter:on */
  }

  private static Integer addAll10(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9, Integer t10) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
  }

  @Test
  public void testCurryingForFunction10WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function10, T1)")
        .when(() ->
          currying(CurryTest::addAll10, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Function9.class)
        )
        .then(actual ->
          assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isEqualTo(1_111_111_111)
        );
    /* @formatter:on */

  }

  private static boolean allPositive2(Integer t1, Integer t2) {
    return t1 > 0 && t2 > 0;
  }
  @Test
  public void testCurryingForPredicate2WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate2, t1)", "Test Curry.currying(Predicate2, T1)")
        .when(() ->
          currying(CurryTest::allPositive2, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate.class)
        )
        .then(actual ->
          assertThat(actual.test(10)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive3(Integer t1, Integer t2, Integer t3) {
    return t1 > 0 && t2 > 0 && t3 > 0;
  }
  @Test
  public void testCurryingForPredicate3WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate3, t1)", "Test Curry.currying(Predicate3, T1)")
        .when(() ->
          currying(CurryTest::allPositive3, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate2.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive4(Integer t1, Integer t2, Integer t3, Integer t4) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0;
  }
  @Test
  public void testCurryingForPredicate4WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate4, t1)", "Test Curry.currying(Predicate4, T1)")
        .when(() ->
          currying(CurryTest::allPositive4, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate3.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive5(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0;
  }
  @Test
  public void testCurryingForPredicate5WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate5, t1)", "Test Curry.currying(Predicate5, T1)")
        .when(() ->
          currying(CurryTest::allPositive5, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate4.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000, 10_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive6(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0 && t6 > 0;
  }
  @Test
  public void testCurryingForPredicate6WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate6, t1)", "Test Curry.currying(Predicate6, T1)")
        .when(() ->
          currying(CurryTest::allPositive6, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate5.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000, 10_000, 100_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive7(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0 && t6 > 0 && t7 > 0;
  }
  @Test
  public void testCurryingForPredicate7WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate7, t1)", "Test Curry.currying(Predicate7, T1)")
        .when(() ->
          currying(CurryTest::allPositive7, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate6.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000, 10_000, 100_000, 1_000_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive8(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0 && t6 > 0 && t7 > 0 && t8 > 0;
  }
  @Test
  public void testCurryingForPredicate8WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate8, t1)", "Test Curry.currying(Predicate8, T1)")
        .when(() ->
          currying(CurryTest::allPositive8, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate7.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive9(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0 && t6 > 0 && t7 > 0 && t8 > 0 && t9 > 0;
  }
  @Test
  public void testCurryingForPredicate9WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate9, t1)", "Test Curry.currying(Predicate9, T1)")
        .when(() ->
          currying(CurryTest::allPositive9, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate8.class)
        )
        .then(actual ->
          assertThat(actual.test(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000)).isTrue()
        );
    /* @formatter:on */

  }

  private static boolean allPositive10(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9, Integer t10) {
    return t1 > 0 && t2 > 0 && t3 > 0 && t4 > 0 && t5 > 0 && t6 > 0 && t7 > 0 && t8 > 0 && t9 > 0 && t10 > 0;
  }
  @Test
  public void testCurryingForPredicate10WithMethodReference() throws Exception {
    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikePredicate10, t1)", "Test Curry.currying(Predicate10, T1)")
        .when(() ->
          currying(CurryTest::allPositive10, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Predicate9.class)
        )
        .then(actual ->
                assertThat(actual.test(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isTrue()
        );
    /* @formatter:on */

  }

  static class PojoForConsumerTesting {
    private final ContainerStoringOnlyOnce<Integer> resultHolder = containerStoringOnlyOnce();

    public Integer getValue() {
      return resultHolder.getValue();
    }

    void consumeAll2(Integer t1, Integer t2) {
      resultHolder.store(t1 + t2);
    }

    void consumeAll3(Integer t1, Integer t2, Integer t3) {
      resultHolder.store(t1 + t2 + t3);
    }

    void consumeAll4(Integer t1, Integer t2, Integer t3, Integer t4) {
      resultHolder.store(t1 + t2 + t3 + t4);
    }

    void consumeAll5(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5);
    }

    void consumeAll6(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5 + t6);
    }

    void consumeAll7(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5 + t6 + t7);
    }

    void consumeAll8(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8);
    }

    void consumeAll9(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9);
    }

    void consumeAll10(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9, Integer t10) {
      resultHolder.store(t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10);
    }
  }

  @Test
  public void testCurryingForConsumer2WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 11;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer2, t1)", "Test Curry.currying(Consumer2, T1)")
        .when(() ->
          currying(result::consumeAll2, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer.class)
        )
        .then(actual -> {
          actual.accept(10);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer3WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer3, t1)", "Test Curry.currying(Consumer3, T1)")
        .when(() ->
          currying(result::consumeAll3, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer2.class)
        )
        .then(actual -> {
          actual.accept(10, 100);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer4WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 1_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer4, t1)", "Test Curry.currying(Consumer4, T1)")
        .when(() ->
          currying(result::consumeAll4, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer3.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer5WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 11_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer5, t1)", "Test Curry.currying(Consumer5, T1)")
        .when(() ->
          currying(result::consumeAll5, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer4.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer6WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 111_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer6, t1)", "Test Curry.currying(Consumer6, T1)")
        .when(() ->
          currying(result::consumeAll6, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer5.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000, 100_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer7WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 1_111_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer7, t1)", "Test Curry.currying(Consumer7, T1)")
        .when(() ->
          currying(result::consumeAll7, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer6.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer8WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 11_111_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer8, t1)", "Test Curry.currying(Consumer8, T1)")
        .when(() ->
          currying(result::consumeAll8, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer7.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer9WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 111_111_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer9, t1)", "Test Curry.currying(Consumer9, T1)")
        .when(() ->
          currying(result::consumeAll9, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer8.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

  @Test
  public void testCurryingForConsumer10WithMethodReference() throws Exception {
    /* Given */
    final PojoForConsumerTesting result = new PojoForConsumerTesting();
    final int expected = 1_111_111_111;

    /* @formatter:off */
    test("currying(SomeType::methodReferenceLikeConsumer10, t1)", "Test Curry.currying(Consumer10, T1)")
        .when(() ->
          currying(result::consumeAll10, 1)
        )
        .then(actual ->
          assertThat(actual).isInstanceOf(Consumer9.class)
        )
        .then(actual -> {
          actual.accept(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000);
          assertThat(result.getValue()).isEqualTo(expected);
        });
    /* @formatter:on */

  }

}