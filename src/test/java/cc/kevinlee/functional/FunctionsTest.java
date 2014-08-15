package cc.kevinlee.functional;

import static cc.kevinlee.functional.Functions.*;
import static cc.kevinlee.testosterone.Testosterones.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null."));
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
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null."));
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
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null."));
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
    })
    .expect(throwing(NullPointerException.class)
           .containsMessage("cannot be null."));
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

}
