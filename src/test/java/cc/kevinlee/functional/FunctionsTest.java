package cc.kevinlee.functional;

import static cc.kevinlee.functional.Functions.*;
import static cc.kevinlee.testosterone.Testosterones.*;
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

import org.junit.Test;

public class FunctionsTest {
  @Test
  public void testIsNull() {
    /* given */
    /* when */
    final Predicate<String> actual = Functions.isNull();

    /* then */
    assertThat(actual.test(null)).isTrue();
  }

  @Test
  public void testIsNull2() {
    /* given */
    final String string = null;

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testIsNull3() {
    /* given */
    final String string = "Some string";

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNull4() {
    /* given */
    final String string = "";

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNotNull() {
    /* given */
    /* when */
    final Predicate<String> actual = Functions.isNotNull();

    /* then */
    assertThat(actual.test(null)).isFalse();
  }

  @Test
  public void testIsNotNull2() {
    /* given */
    final String string = null;

    /* when */
    final boolean actual = Functions.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNotNull3() {
    /* given */
    final String string = "Some string";

    /* when */
    final boolean actual = Functions.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testIsNotNull4() {
    /* given */
    final String string = "";

    /* when */
    final boolean actual = Functions.isNotNull()
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

    /* when */
    final List<String> actual = words.stream()
        .filter(not(text::contains))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
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
        .filter(not(FunctionsTest::positiveNumber))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testNotWithNull() {
    /* given */
    final Predicate<Integer> nullPredicate = null;

    when(() -> {
      not(nullPredicate);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
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

    final Comparator<?> nullComparator = null;

    when(() -> {
      reversed(nullComparator);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
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
  public void testCompare() {
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
        .sorted(compare(SomeBean::getDateCreated))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testCompareWithNull() {

    final Function<Object, String> nullFunction = null;

    when(() -> {
      compare(nullFunction);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
  }

  @Test
  public void testCompareReversed() {
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
        .sorted(reversed(compare(SomeBean::getDateCreated)))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testCompareLt() {
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
    final int actual = compare(SomeBean::getDateCreated).compare(madeYesterday, willBeMadeTomorrow);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testCompareEq() {
    /* given */
    final LocalDate today = LocalDate.now();

    final SomeBean madeYesterday = new SomeBean(today);
    final SomeBean willBeMadeTomorrow = new SomeBean(today);

    final int expected = today.compareTo(today);

    /* when */
    final int actual = compare(SomeBean::getDateCreated).compare(madeYesterday, willBeMadeTomorrow);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testCompareGt() {
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
    final int actual = compare(SomeBean::getDateCreated).compare(willBeMadeTomorrow, madeYesterday);

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

    when(() -> {
      toStringOf(nullFunction);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null."));
  }

  @Test
  public void testToTrue() {
    /* given */
    final String value = "test";
    /* when */
    final Function<String, Boolean> toTrue = Functions.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue2() {
    /* given */
    final Integer value = 1;
    /* when */
    final Function<Integer, Boolean> toTrue = Functions.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue3() {
    /* given */
    final long value = 1L;
    /* when */
    final Function<Long, Boolean> toTrue = Functions.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue4() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<Boolean, Boolean> toTrue = Functions.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToTrue5() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<? super Object, Boolean> toTrue = Functions.toTrue();
    final boolean actual = toTrue.apply(value);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testToFalse() {
    /* given */
    final String value = "test";
    /* when */
    final Function<String, Boolean> toFalse = Functions.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse2() {
    /* given */
    final Integer value = 1;
    /* when */
    final Function<Integer, Boolean> toFalse = Functions.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse3() {
    /* given */
    final long value = 1L;
    /* when */
    final Function<Long, Boolean> toFalse = Functions.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse4() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<Boolean, Boolean> toFalse = Functions.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testToFalse5() {
    /* given */
    final Boolean value = false;
    /* when */
    final Function<? super Object, Boolean> toFalse = Functions.toFalse();
    final boolean actual = toFalse.apply(value);

    /* then */
    assertThat(actual).isFalse();
  }

  static class TestBean {
    final String name;

    TestBean(final String name) {
      this.name = name;
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
  public void testUsing1WithNull() {
    when(() -> {
      Functions.using(null, param1);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsingWithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1), new TestBean("def").call(param1),
        new TestBean("ghi").call(param1));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing2WithNull() {
    when(() -> {
      Functions.using(null, param1, param2);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing2() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing2WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2), new TestBean("def").call(param1, param2),
        new TestBean("ghi").call(param1, param2));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing3WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing3() {
    /* given */
    final String name = "abc";

    final String expected = new TestBean(name).call(param1, param2, param3);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing3WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3),
        new TestBean("def").call(param1, param2, param3), new TestBean("ghi").call(param1, param2, param3));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing4WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing4() {
    /* given */
    final String name = "abc";

    final String expected = new TestBean(name).call(param1, param2, param3, param4);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing4WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4),
        new TestBean("def").call(param1, param2, param3, param4), new TestBean("ghi").call(param1, param2, param3, param4));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing5WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4, param5);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing5() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4, param5);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing5WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5),
        new TestBean("def").call(param1, param2, param3, param4, param5), new TestBean("ghi").call(param1, param2, param3, param4, param5));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4, param5))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing6WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4, param5, param6);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing6() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4, param5, param6);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing6WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6), new TestBean(
        "def").call(param1, param2, param3, param4, param5, param6), new TestBean("ghi").call(param1, param2, param3, param4, param5,
        param6));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4, param5, param6))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing7WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4, param5, param6, param7);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing7() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4, param5, param6, param7);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing7WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4, param5, param6, param7))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing8WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4, param5, param6, param7, param8);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing8() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7, param8);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing8WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7, param8),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7, param8),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7, param8));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing9WithNull() {
    when(() -> {
      Functions.using(null, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    }).expect(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testUsing9() {
    /* given */
    final String name = "abc";
    final String expected = new TestBean(name).call(param1, param2, param3, param4, param5, param6, param7, param8, param9);

    /* when */
    final Function<TestBean, String> f = Functions.using(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8,
        param9);
    final String actual = f.apply(new TestBean(name));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testUsing9WithStream() {
    /* given */
    final List<TestBean> list = Arrays.asList(new TestBean("abc"), new TestBean("def"), new TestBean("ghi"));

    final List<String> expected = Arrays.asList(
        new TestBean("abc").call(param1, param2, param3, param4, param5, param6, param7, param8, param9),
        new TestBean("def").call(param1, param2, param3, param4, param5, param6, param7, param8, param9),
        new TestBean("ghi").call(param1, param2, param3, param4, param5, param6, param7, param8, param9));

    /* when */
    final List<String> actual = list.stream()
        .map(using(TestBean::call, param1, param2, param3, param4, param5, param6, param7, param8, param9))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testAcceptingWithNull() {
    when(() -> {
      Functions.accepting(null, param1);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting() {
    /* given */
    final Integer param1 = 999;

    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1);
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
    when(() -> {
      Functions.accepting(null, param1, param2);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting2() {
    /* given */
    final Integer param1 = 999;
    final String param2 = "{";
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting3() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting4() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4, param5);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting5() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4, param5);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4, param5, param6);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting6() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4, param5, param6);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4, param5, param6, param7);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting7() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting8() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8);
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
    when(() -> {
      Functions.accepting(null, param1, param2, param3, param4, param5, param6, param7, param8, param9);
    }).equals(throwing(NullPointerException.class).containsMessage("cannot be null"));
  }

  @Test
  public void testAccepting9() {
    /* given */
    final TestBean testBean = mock(TestBean.class);

    /* when */
    final Consumer<TestBean> r = Functions.accepting(TestBean::run, param1, param2, param3, param4, param5, param6, param7, param8, param9);
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
