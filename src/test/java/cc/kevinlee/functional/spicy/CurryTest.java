package cc.kevinlee.functional.spicy;

import cc.kevinlee.functional.types.*;
import org.elixirian.kommonlee.test.CommonTestHelper;
import org.junit.Test;

import java.util.function.BiFunction;

import static cc.kevinlee.functional.spicy.Curry.*;
import static cc.kevinlee.testosterone.Testosterone.*;

import static org.assertj.core.api.Assertions.*;


/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-06-22
 */
public class CurryTest {

  @Test
  public void testCurry() throws Exception {
    test("test Curry()", "Curry() must be private")
      .when(() ->
        CommonTestHelper.newConstructorTester(Curry.class, this).mustBePrivate().test()
      )
      .expect(throwing(IllegalAccessException.class));
  }

  @Test
  public void testCurrying() throws Exception {
    final BiFunction<Integer, Integer, Integer> biFunction =
        (t1, t2) -> t1 + t2;
    test("currying(biFunction, t1)", "Test Curry.currying(BiFunction, T1)")
        .when(() ->
                currying(biFunction, 1)
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
                assertThat(actual.apply(10)).isEqualTo(11)
        );
    test("currying(Function2 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll2(Integer t1, Integer t2) {
    return t1 + t2;
  }

  @Test
  public void testCurryingWithMethodReference() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(BiFunction, T1)")
        .when(() ->
                currying(CurryTest::addAll2, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10)).isEqualTo(11)
        );

  }

  @Test
  public void testCurrying1() throws Exception {
    final Function3<Integer, Integer, Integer, Integer> function =
        (t1, t2, t3) -> t1 + t2 + t3;
    final Function3<Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function3, t1)", "Test Curry.currying(Function3, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100)).isEqualTo(111)
        );
    test("currying(Function3 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll3(Integer t1, Integer t2, Integer t3) {
    return t1 + t2 + t3;
  }

  @Test
  public void testCurryingWithMethodReference1() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function3, T1)")
        .when(() ->
                currying(CurryTest::addAll3, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100)).isEqualTo(111)
        );

  }

  @Test
  public void testCurrying2() throws Exception {
    final Function4<Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4) -> t1 + t2 + t3 + t4;
    final Function4<Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function4, t1)", "Test Curry.currying(Function4, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000)).isEqualTo(1_111)
        );
    test("currying(Function4 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll4(Integer t1, Integer t2, Integer t3, Integer t4) {
    return t1 + t2 + t3 + t4;
  }

  @Test
  public void testCurryingWithMethodReference2() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function4, T1)")
        .when(() ->
                currying(CurryTest::addAll4, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000)).isEqualTo(1_111)
        );

  }

  @Test
  public void testCurrying3() throws Exception {
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5) -> t1 + t2 + t3 + t4 + t5;
    final Function5<Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function5, t1)", "Test Curry.currying(Function5, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000)).isEqualTo(11_111)
        );
    test("currying(Function5 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll5(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5) {
    return t1 + t2 + t3 + t4 + t5;
  }

  @Test
  public void testCurryingWithMethodReference3() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function5, T1)")
        .when(() ->
                currying(CurryTest::addAll5, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000)).isEqualTo(11_111)
        );

  }

  @Test
  public void testCurrying4() throws Exception {
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6) -> t1 + t2 + t3 + t4 + t5 + t6;
    final Function6<Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function6, t1)", "Test Curry.currying(Function6, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000)).isEqualTo(111_111)
        );
    test("currying(Function6 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll6(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
    return t1 + t2 + t3 + t4 + t5 + t6;
  }

  @Test
  public void testCurryingWithMethodReference4() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function6, T1)")
        .when(() ->
                currying(CurryTest::addAll6, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000)).isEqualTo(111_111)
        );

  }

  @Test
  public void testCurrying5() throws Exception {
    final Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7) -> t1 + t2 + t3 + t4 + t5 + t6 + t7;
    final Function7<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function7, t1)", "Test Curry.currying(Function7, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000)).isEqualTo(1_111_111)
        );
    test("currying(Function7 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll7(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7;
  }

  @Test
  public void testCurryingWithMethodReference5() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function7, T1)")
        .when(() ->
                currying(CurryTest::addAll7, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000)).isEqualTo(1_111_111)
        );

  }

  @Test
  public void testCurrying6() throws Exception {
    final Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;
    final Function8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function8, t1)", "Test Curry.currying(Function8, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000)).isEqualTo(11_111_111)
        );
    test("currying(Function8 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll8(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8;
  }

  @Test
  public void testCurryingWithMethodReference6() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function8, T1)")
        .when(() ->
                currying(CurryTest::addAll8, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000)).isEqualTo(11_111_111)
        );

  }

  @Test
  public void testCurrying7() throws Exception {
    final Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
    final Function9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function9, t1)", "Test Curry.currying(Function9, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000)).isEqualTo(111_111_111)
        );
    test("currying(Function9 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll9(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9;
  }

  @Test
  public void testCurryingWithMethodReference7() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function9, T1)")
        .when(() ->
                currying(CurryTest::addAll9, 1)
        )
    .then(actual ->
            assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000)).isEqualTo(111_111_111)
    );

  }

  @Test
  public void testCurrying8() throws Exception {
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> function =
        (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
    final Function10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> nullFunction = null;
    test("currying(function10, t1)", "Test Curry.currying(Function10, T1)")
        .when(() ->
                currying(function, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isEqualTo(1_111_111_111)
        );
    test("currying(Function10 == null, t1)", "Test Curry.currying(null, T1)")
        .when(() ->
                currying(nullFunction, 1)
        )
        .expect(throwing(NullPointerException.class));
  }

  private static Integer addAll10(Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6, Integer t7, Integer t8, Integer t9, Integer t10) {
    return t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10;
  }

  @Test
  public void testCurryingWithMethodReference8() throws Exception {
    test("currying(SomeType::methodReference, t1)", "Test Curry.currying(Function10, T1)")
        .when(() ->
                currying(CurryTest::addAll10, 1)
        )
        .then(actual ->
                assertThat(actual.apply(10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000)).isEqualTo(1_111_111_111)
        );

  }

}