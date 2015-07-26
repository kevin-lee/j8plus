package cc.kevinlee.functional;

import static cc.kevinlee.functional.Funs.*;
import static cc.kevinlee.testosterone.Testosterone.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.elixirian.kommonlee.test.CommonTestHelper;
import org.junit.Test;

public class FunsTest {

  @Test
  public void testFuns() {
    /* @formatter:off */
    test("test Funs()", "Funs() must be private")
      .when(() ->
        CommonTestHelper.newConstructorTester(Funs.class, this).mustBePrivate().test()
      )
      .expect(throwing(IllegalAccessException.class));
    /* @formatter:on */

  }

  @Test
  public void testIsNull() {
    /* @formatter:off */
    test("testIsNull", "Funs.isNull()")
    .when(() -> Funs.isNull())
    .then(actual -> assertThat(actual.test(null)).isTrue());
    /* @formatter:on */
  }

  @Test
  public void testIsNull2() {
    /* given */
    final String string = null;

    /* @formatter:off */
    test("testIsNull2", "Funs.isNull().test(null)")
    .when(() -> Funs.isNull()
                         .test(string))
    .then(actual -> assertThat(actual).isTrue());
    /* @formatter:on */
  }

  @Test
  public void testIsNull3() {
    /* given */
    final String string = "Some string";

    /* @formatter:off */
    test("testIsNull3", "Funs.isNull().test(" + string + ")")
    .when(() -> Funs.isNull()
                         .test(string))
    .then(actual ->
      assertThat(actual).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public void testIsNull4() {
    /* given */
    final String string = "";

    /* @formatter:off */
    test("testIsNull4", "Funs.isNull().test(\"" + string + "\")")
    .when(() ->
      Funs.isNull().test(string)
    )
    .then(actual ->
      assertThat(actual).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public void testIsNotNull() {
    /* @formatter:off */
    test("testIsNotNull", "Funs.isNotNull()")
    .when(() ->
      Funs.isNotNull()
    )
    .then(actual ->
      assertThat(actual.test(null)).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public void testIsNotNull2() {
    /* given */
    final String string = null;

    /* when */
    final boolean actual = Funs.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNotNull3() {
    /* given */
    final String string = "Some string";

    /* when */
    final boolean actual = Funs.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testIsNotNull4() {
    /* given */
    final String string = "";

    /* when */
    final boolean actual = Funs.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testNot() {
    /* given */
    final List<String> words = Arrays.asList("Hello", "Kevin", "Hi", "How are you?", "Good morning", "This", "That", "do", "do ", "where",
        "which", "does", "not");
    final List<String> expected = Arrays.asList("Hi", "Good morning", "That", "do ", "where");
    final String text = "Hello Kevin. How are you? This will get all the words which this text does not contain.";

    /* @formatter:off */
    test("testNot", "Test not() with stream and method reference")
    .when(() ->
        words.stream()
        .filter(not(text::contains))
        .collect(toList())
    )
    .then(actual ->
      assertThat(actual).isEqualTo(expected)
    );
    /* @formatter:on */
  }

  @Test
  public final void testNot2() {
    /* given */
    final List<Integer> numbers = Arrays.asList(-11, 4, -3, 2, 0, 3, -7, 10, -8, 32, 99, 57, 100, -88);
    final List<Integer> expected = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);

    final Predicate<Integer> negativeNumber = i -> i < 0;

    /* when */
    final List<Integer> actual = numbers.stream()
        .filter(not(negativeNumber))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  private static boolean positiveNumber(final int number) {
    return number > 0;
  }

  @Test
  public final void testNot3() {
    /* given */
    final List<Integer> numbers = Arrays.asList(-11, 4, -3, 2, 0, 3, -7, 10, -8, 32, 99, 57, 100, -88);
    final List<Integer> expected = Arrays.asList(-11, -3, 0, -7, -8, -88);

    /* when */
    final List<Integer> actual = numbers.stream()
        .filter(not(FunsTest::positiveNumber))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testNotWithNull() {
    /* given */
    final Predicate<Integer> nullPredicate = null;

    /* @formatter:off */
    test("testNotWithNull", "not(null)")
    .when(() -> {
      not(nullPredicate);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
    /* @formatter:on */
  }

  @Test
  public final void testReversed() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(100, 99, 57, 32, 10, 4, 3, 2, 0);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(Integer::compareTo))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReversedWithoutStream() {
    /* given */
    final int number1 = 1;
    final int number2 = 2;
    final int expected = 1;

    final Comparator<Integer> integerComparator = Integer::compareTo;
    final Comparator<Integer> reversedIntegerComparator = reversed(integerComparator);

    test("Test reversed()", "reversed(Integer::compareTo)")
        .when(
            () -> reversedIntegerComparator.compare(number1, number2)
        )
        .then(actual ->
            assertThat(actual).isEqualTo(expected)
        );
  }

  @Test
  public final void testReversed2() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(0, 2, 3, 4, 10, 32, 57, 99, 100);

    final Comparator<Integer> descendingOrder = (i1, i2) -> i2.compareTo(i1);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(descendingOrder))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  private int ascendingOrder(final int i1, final int i2) {
    return Integer.compare(i1, i2);
  }

  @Test
  public final void testReversed3() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(100, 99, 57, 32, 10, 4, 3, 2, 0);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(this::ascendingOrder))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReversedWithNullComparator() {

    final Comparator<Object> nullComparator = null;

    /* @formatter:off */
    test("testReversedWithNullComparator", "reversed(null)")
    .when(() -> {
      reversed(nullComparator);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
    /* @formatter:on */
  }

  static class SomeBean {
    private final LocalDate dateCreated;

    public SomeBean(final LocalDate localDate) {
      this.dateCreated = localDate;
    }

    public LocalDate getDateCreated() {
      return dateCreated;
    }

    @Override
    public String toString() {
      return dateCreated.toString();
    }
  }

  @Test
  public void testComparing() {
    /* given */

    /* @formatter:off */
    final SomeBean someBean1 = new SomeBean(LocalDate.now()
                                                     .minusDays(2));
    final SomeBean someBean2 = new SomeBean(LocalDate.now()
                                                     .minusDays(1));
    final SomeBean someBean3 = new SomeBean(LocalDate.now());
    /* @formatter:on */

    final List<SomeBean> expected = Arrays.asList(someBean1, someBean2, someBean3);

    /* when */
    final List<SomeBean> actual = Arrays.asList(someBean3, someBean1, someBean2)
        .stream()
        .sorted(comparing(SomeBean::getDateCreated))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testComparingWithNull() {

    final Function<Object, String> nullFunction = null;

    /* @formatter:off */
    test("testComparingWithNull", "comparing(null)")
    .when(() -> {
      comparing(nullFunction);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
    /* @formatter:on */
  }

  @Test
  public void testComparingReversed() {
    /* given */

    /* @formatter:off */
    final SomeBean someBean1 = new SomeBean(LocalDate.now()
                                                     .minusDays(2));
    final SomeBean someBean2 = new SomeBean(LocalDate.now()
                                                     .minusDays(1));
    final SomeBean someBean3 = new SomeBean(LocalDate.now());
    /* @formatter:on */

    final List<SomeBean> expected = Arrays.asList(someBean3, someBean2, someBean1);

    /* when */
    final List<SomeBean> actual = Arrays.asList(someBean1, someBean2, someBean3)
        .stream()
        .sorted(reversed(comparing(SomeBean::getDateCreated)))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testComparingLt() {
    /* given */

    /* @formatter:off */
    final LocalDate yesterday = LocalDate.now()
                                         .minusDays(1);
    final LocalDate tomorrow =  LocalDate.now()
                                         .plusDays(1);
    /* @formatter:on */

    final SomeBean madeYesterday = new SomeBean(yesterday);
    final SomeBean willBeMadeTomorrow = new SomeBean(tomorrow);

    final int expected = yesterday.compareTo(tomorrow);

    /* when */
    final int actual = comparing(SomeBean::getDateCreated).compare(madeYesterday, willBeMadeTomorrow);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testComparingEq() {
    /* given */
    final LocalDate today = LocalDate.now();

    final SomeBean madeYesterday = new SomeBean(today);
    final SomeBean willBeMadeTomorrow = new SomeBean(today);

    final int expected = today.compareTo(today);

    /* when */
    final int actual = comparing(SomeBean::getDateCreated).compare(madeYesterday, willBeMadeTomorrow);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testComparingGt() {
    /* given */

    /* @formatter:off */
    final LocalDate yesterday = LocalDate.now()
                                         .minusDays(1);
    final LocalDate tomorrow =  LocalDate.now()
                                         .plusDays(1);
    /* @formatter:on */

    final SomeBean madeYesterday = new SomeBean(yesterday);
    final SomeBean willBeMadeTomorrow = new SomeBean(tomorrow);

    final int expected = tomorrow.compareTo(yesterday);

    /* when */
    final int actual = comparing(SomeBean::getDateCreated).compare(willBeMadeTomorrow, madeYesterday);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  static class TestValue {
    private final Long id;

    public TestValue(final Long id) {
      this.id = id;
    }

    public Long getId() {
      return id;
    }
  }

  @Test
  public void testToStringOf() {
    /* given */
    final List<TestValue> numbers = Arrays.asList(new TestValue(1L), new TestValue(2L), new TestValue(3L));
    final String expected = "[1, 2, 3]";

    /* when */
    final String actual = numbers.stream()
        .map(toStringOf(TestValue::getId))
        .collect(joining(", ", "[", "]"));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testToStringOfWithNull() {
    /* given */
    final List<TestValue> numbers = Arrays.asList(new TestValue(1L), new TestValue(null), new TestValue(3L));
    final String expected = "[1, null, 3]";

    /* when */
    final String actual = numbers.stream()
        .map(toStringOf(TestValue::getId))
        .collect(joining(", ", "[", "]"));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testToStringOfWithNullFunction() {
    /* given */
    final Function<Object, String> nullFunction = null;

    /* @formatter:off */
    test("testToStringOfWithNullFunction", "toStringOf(null)")
    .when(() -> {
      toStringOf(nullFunction);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
    /* @formatter:on */
  }

  @Test
  public void testToTrue() {
    /* given */
    final String value = "test";
    /* when */
    final Function<String, Boolean> toTrue = Funs.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue2() {
    /* given */
    final Integer value = 1;
    /* when */
    final Function<Integer, Boolean> toTrue = Funs.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue3() {
    /* given */
    final long value = 1L;
    /* when */
    final Function<Long, Boolean> toTrue = Funs.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue4() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<Boolean, Boolean> toTrue = Funs.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue5() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<? super Object, Boolean> toTrue = Funs.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToFalse() {
    /* given */
    final String value = "test";
    /* when */
    final Function<String, Boolean> toFalse = Funs.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse2() {
    /* given */
    final Integer value = 1;
    /* when */
    final Function<Integer, Boolean> toFalse = Funs.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse3() {
    /* given */
    final long value = 1L;
    /* when */
    final Function<Long, Boolean> toFalse = Funs.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse4() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<Boolean, Boolean> toFalse = Funs.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse5() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<? super Object, Boolean> toFalse = Funs.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  static class TestBean {
    final String name;

    TestBean(final String name) {
      this.name = name;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8) {
      return false;
    }

    boolean isOk(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8,
        @SuppressWarnings("unused") final String param9) {
      return false;
    }

    String call(final Integer number) {
      return name + "+" + number;
    }

    String call(final Integer number, final String prefix) {
      return prefix + ":" + name + "+" + number;
    }

    String call(final Integer number, final String prefix, final String suffix) {
      return prefix + ":" + name + "+" + number + suffix;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
        final String param6) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
        final String param6, final String param7) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
        final String param6, final String param7, final String param8) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7 + "-" + param8;
    }

    String call(final Integer number, final String prefix, final String suffix, final String param4, final String param5,
        final String param6, final String param7, final String param8, final String param9) {
      return prefix + ":" + name + "+" + number + suffix + " - " + param4 + " - " + param5 + "-" + param6 + "-" + param7 + "-" + param8
          + "-" + param9;
    }

    void run(@SuppressWarnings("unused") final Integer number) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8) {
      throw new AssertionError("Use mock to test");
    }

    void run(@SuppressWarnings("unused") final Integer number, @SuppressWarnings("unused") final String prefix,
        @SuppressWarnings("unused") final String suffix, @SuppressWarnings("unused") final String param4,
        @SuppressWarnings("unused") final String param5, @SuppressWarnings("unused") final String param6,
        @SuppressWarnings("unused") final String param7, @SuppressWarnings("unused") final String param8,
        @SuppressWarnings("unused") final String param9) {
      throw new AssertionError("Use mock to test");
    }
  }

  final Integer param1 = 999;
  final String param2 = "{";
  final String param3 = "}";
  final String param4 = "aaa";
  final String param5 = "bbb";
  final String param6 = "ccc";
  final String param7 = "ddd";
  final String param8 = "eee";
  final String param9 = "fff";

  @Test
  public void testSatisfyingWithNull() {
    /* @formatter:off */
    test("testSatisfyingWithNull", "Funs.satisfying(null, param1)")
    .when(() -> {
      Funs.satisfying(null, param1);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1);
  }

  @Test
  public void testSatisfyingWithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1)).thenReturn(true);
    when(testBean2.isOk(param1)).thenReturn(true);
    when(testBean3.isOk(param1)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1);
    verify(testBean2, times(1)).isOk(param1);
    verify(testBean3, times(1)).isOk(param1);
  }

  @Test
  public void testSatisfyingWithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1)).thenReturn(false);
    when(testBean2.isOk(param1)).thenReturn(false);
    when(testBean3.isOk(param1)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1);
    verify(testBean2, times(1)).isOk(param1);
    verify(testBean3, times(1)).isOk(param1);
  }

  @Test
  public void testSatisfying2WithNull() {
    /* @formatter:off */
    test("testSatisfying2WithNull", "Funs.satisfying(null, param1, param2)")
    .when(() -> {
      Funs.satisfying(null, param1, param2);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2);
  }

  @Test
  public void testSatisfying2WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2)).thenReturn(true);
    when(testBean2.isOk(param1, param2)).thenReturn(true);
    when(testBean3.isOk(param1, param2)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2);
    verify(testBean2, times(1)).isOk(param1, param2);
    verify(testBean3, times(1)).isOk(param1, param2);
  }

  @Test
  public void testSatisfying2WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2)).thenReturn(false);
    when(testBean2.isOk(param1, param2)).thenReturn(false);
    when(testBean3.isOk(param1, param2)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2);
    verify(testBean2, times(1)).isOk(param1, param2);
    verify(testBean3, times(1)).isOk(param1, param2);
  }

  @Test
  public void testSatisfying3WithNull() {
    /* @formatter:off */
    test("testSatisfying3WithNull", "Funs.satisfying(null, param1, param2, param3)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying3() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3);
  }

  @Test
  public void testSatisfying3WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3);
    verify(testBean2, times(1)).isOk(param1, param2, param3);
    verify(testBean3, times(1)).isOk(param1, param2, param3);
  }

  @Test
  public void testSatisfying3WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3);
    verify(testBean2, times(1)).isOk(param1, param2, param3);
    verify(testBean3, times(1)).isOk(param1, param2, param3);
  }

  @Test
  public void testSatisfying4WithNull() {
    /* @formatter:off */
    test("testSatisfying4WithNull", "Funs.satisfying(null, param1, param2, param3, param4)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying4() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4);
  }

  @Test
  public void testSatisfying4WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4);
  }

  @Test
  public void testSatisfying4WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4);
  }

  @Test
  public void testSatisfying5WithNull() {
    /* @formatter:off */
    test("testSatisfying5WithNull", "Funs.satisfying(null, param1, param2, param3, param4, param5)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4, param5);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying5() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4, param5)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4, param5);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5);
  }

  @Test
  public void testSatisfying5WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4, param5)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4, param5)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5);
  }

  @Test
  public void testSatisfying5WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4, param5)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4, param5)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5);
  }

  @Test
  public void testSatisfying6WithNull() {
    /* @formatter:off */
    test("testSatisfying6WithNull", "Funs.satisfying(null, param1, param2, param3, param4, param5, param6)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4, param5, param6);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying6() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6);
  }

  @Test
  public void testSatisfying6WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6);
  }

  @Test
  public void testSatisfying6WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6);
  }

  @Test
  public void testSatisfying7WithNull() {
    /* @formatter:off */
    test("testSatisfying7WithNull", "Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying7() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
  }

  @Test
  public void testSatisfying7WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
  }

  @Test
  public void testSatisfying7WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7);
  }

  @Test
  public void testSatisfying8WithNull() {
    /* @formatter:off */
    test("testSatisfying8WithNull", "Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7, param8)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7, param8);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying8() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
  }

  @Test
  public void testSatisfying8WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
  }

  @Test
  public void testSatisfying8WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7, param8)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8);
  }

  @Test
  public void testSatisfying9WithNull() {
    /* @formatter:off */
    test("testSatisfying9WithNull", "Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7, param8, param9)")
    .when(() -> {
      Funs.satisfying(null, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testSatisfying9() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);

    final boolean expected = true;
    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(expected);

    /* when */
    final Predicate<TestBean> predicate = satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    final boolean actual = predicate.test(testBean1);

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }

  @Test
  public void testSatisfying9WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(true);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(true);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(true);

    final List<TestBean> expected = Arrays.asList(testBean1, testBean2, testBean3);

    /* when */
    final List<TestBean> actual = expected.stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8, param9))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }

  @Test
  public void testSatisfying9WithStream2() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    when(testBean1.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(false);
    when(testBean2.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(false);
    when(testBean3.isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9)).thenReturn(false);

    final List<TestBean> expected = Arrays.asList();

    /* when */
    final List<TestBean> actual = Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .filter(satisfying(TestBean::isOk, param1, param2, param3, param4, param5, param6, param7, param8, param9))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
    assertThat(actual).hasSize(0);

    verify(testBean1, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean2, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean3, times(1)).isOk(param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }

  @Test
  public void testApplyingWithNull() {
    /* @formatter:off */
    test("testApplyingWithNull", "Funs.applying(null, param1)")
    .when(() -> {
      Funs.applying(null, param1);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplyingWithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1), new TestBean("def").call(param1),
        new TestBean("ghi").call(param1));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying2WithNull() {
    /* @formatter:off */
    test("testApplying2WithNull", "Funs.applying(null, param1, param2)")
    .when(() -> {
      Funs.applying(null, param1, param2);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying2() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying2WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2), new TestBean("def").call(param1, param2),
        new TestBean("ghi").call(param1, param2));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying3WithNull() {
    /* @formatter:off */
    test("testApplying3WithNull", "Funs.applying(null, param1, param2, param3)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying3() {
    /* given */
    final String name = "abc";

    final String expected = new TestBean(name).call(param1, param2, param3);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying3WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3),
        new TestBean("def").call(param1, param2, param3), new TestBean("ghi").call(param1, param2, param3));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying4WithNull() {
    /* @formatter:off */
    test("testApplying4WithNull", "Funs.applying(null, param1, param2, param3, param4)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying4() {
    /* given */
    final String name = "abc";

    final String expected = new TestBean(name).call(param1, param2, param3, param4);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying4WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4),
        new TestBean("def").call(param1, param2, param3, param4), new TestBean("ghi").call(param1, param2, param3, param4));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying5WithNull() {
    /* @formatter:off */
    test("testApplying5WithNull", "Funs.applying(null, param1, param2, param3, param4, param5)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4, param5);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying5() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4, param5);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying5WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5),
        new TestBean("def").call(param1, param2, param3, param4, param5), new TestBean("ghi").call(param1, param2, param3, param4, param5));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4, param5))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying6WithNull() {
    /* @formatter:off */
    test("testApplying6WithNull", "Funs.applying(null, param1, param2, param3, param4, param5, param6)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4, param5, param6);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying6() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4, param5, param6);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying6WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6), new TestBean(
        "def").call(param1, param2, param3, param4, param5, param6), new TestBean("ghi").call(param1, param2, param3, param4, param5,
        param6));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4, param5, param6))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying7WithNull() {
    /* @formatter:off */
    test("testApplying7WithNull", "Funs.applying(null, param1, param2, param3, param4, param5, param6, param7)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4, param5, param6, param7);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying7() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying7WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying8WithNull() {
    /* @formatter:off */
    test("testApplying8WithNull", "Funs.applying(null, param1, param2, param3, param4, param5, param6, param7, param8)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4, param5, param6, param7, param8);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying8() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7, param8);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying8WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7, param8),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7, param8),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7, param8));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying9WithNull() {
    /* @formatter:off */
    test("testApplying9WithNull", "Funs.applying(null, param1, param2, param3, param4, param5, param6, param7, param8, param9)")
    .when(() -> {
      Funs.applying(null, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testApplying9() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7, param8, param9);

    /* when */
    final Function<TestBean, String> f = Funs.applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8,
        param9);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testApplying9WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(
        new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7, param8, param9),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7, param8, param9),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7, param8, param9));

    /* when */
    final List<String> actual = list.stream()
        .map(applying(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8, param9))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testAcceptingWithNull() {
    /* @formatter:off */
    test("testAcceptingWithNull", "Funs.accepting(null, param1)")
    .when(() -> {
      Funs.accepting(null, param1);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting() {
    /* given */
    final Integer param1 = 999;

    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1);
  }

  @Test
  public void testAcceptingWithStream() {
    /* given */
    final Integer param1 = 999;

    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1));

    /* then */
    verify(testBean1, times(1)).run(param1);
    verify(testBean2, times(1)).run(param1);
    verify(testBean3, times(1)).run(param1);
  }

  @Test
  public void testAccepting2WithNull() {
    /* @formatter:off */
    test("testAccepting2WithNull", "Funs.accepting(null, param1, param2)")
    .when(() -> {
      Funs.accepting(null, param1, param2);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting2() {
    /* given */
    final Integer param1 = 999;
    final String param2 = "{";
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2);
  }

  @Test
  public void testAccepting2WithStream() {
    /* given */
    final Integer param1 = 999;
    final String param2 = "{";

    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2));

    /* then */
    verify(testBean1, times(1)).run(param1, param2);
    verify(testBean2, times(1)).run(param1, param2);
    verify(testBean3, times(1)).run(param1, param2);
  }

  @Test
  public void testAccepting3WithNull() {
    test("testAccepting3WithNull", "Funs.accepting(null, param1, param2, param3)").when(() -> {
      Funs.accepting(null, param1, param2, param3);
    })
        .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting3() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3);
  }

  @Test
  public void testAccepting3WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3);
    verify(testBean2, times(1)).run(param1, param2, param3);
    verify(testBean3, times(1)).run(param1, param2, param3);
  }

  @Test
  public void testAccepting4WithNull() {
    /* @formatter:off */
    test("testAccepting4WithNull", "Funs.accepting(null, param1, param2, param3, param4)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting4() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4);
  }

  @Test
  public void testAccepting4WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4);
    verify(testBean2, times(1)).run(param1, param2, param3, param4);
    verify(testBean3, times(1)).run(param1, param2, param3, param4);
  }

  @Test
  public void testAccepting5WithNull() {
    /* @formatter:off */
    test("testAccepting5WithNull", "Funs.accepting(null, param1, param2, param3, param4, param5)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4, param5);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting5() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4, param5);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4, param5);
  }

  @Test
  public void testAccepting5WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4, param5));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4, param5);
    verify(testBean2, times(1)).run(param1, param2, param3, param4, param5);
    verify(testBean3, times(1)).run(param1, param2, param3, param4, param5);
  }

  @Test
  public void testAccepting6WithNull() {
    /* @formatter:off */
    test("testAccepting6WithNull", "Funs.accepting(null, param1, param2, param3, param4, param5, param6)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4, param5, param6);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting6() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4, param5, param6);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4, param5, param6);
  }

  @Test
  public void testAccepting6WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4, param5, param6));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4, param5, param6);
    verify(testBean2, times(1)).run(param1, param2, param3, param4, param5, param6);
    verify(testBean3, times(1)).run(param1, param2, param3, param4, param5, param6);
  }

  @Test
  public void testAccepting7WithNull() {
    /* @formatter:off */
    test("testAccepting7WithNull", "Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting7() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4, param5, param6, param7);
  }

  @Test
  public void testAccepting7WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean2, times(1)).run(param1, param2, param3, param4, param5, param6, param7);
    verify(testBean3, times(1)).run(param1, param2, param3, param4, param5, param6, param7);
  }

  @Test
  public void testAccepting8WithNull() {
    /* @formatter:off */
    test("testAccepting8WithNull", "Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting8() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8);
  }

  @Test
  public void testAccepting8WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean2, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8);
    verify(testBean3, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8);
  }

  @Test
  public void testAccepting9WithNull() {
    /* @formatter:off */
    test("testAccepting9WithNull", "Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8, param9)")
    .when(() -> {
      Funs.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    })
    .expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
    /* @formatter:on */
  }

  @Test
  public void testAccepting9() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Funs.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    r.accept(testBean);

    /* then */
    verify(testBean, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }

  @Test
  public void testAccepting9WithStream() {
    /* given */
    final TestBean testBean1 = mock(TestBean.class);
    final TestBean testBean2 = mock(TestBean.class);
    final TestBean testBean3 = mock(TestBean.class);

    /* when */
    Arrays.asList(testBean1, testBean2, testBean3)
        .stream()
        .forEach(accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8, param9));

    /* then */
    verify(testBean1, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean2, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8, param9);
    verify(testBean3, times(1)).run(param1, param2, param3, param4, param5, param6, param7, param8, param9);
  }

}
