package j8plus;

import static testosterone.Testosterone.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class BigIntegersTest {

  @Test
  public final void testLtBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* @formatter:off */
    test("testLtBigInteger", "Numbers.BigIntegers.lt(new BigInteger(\"100\")).test(new BigInteger(\"99\")) should be true")
    .when(() ->
      Numbers.BigIntegers.lt(new BigInteger("100"))
    )
    .then(actual ->
      assertThat(actual.test(number)).isTrue()
    );
    /* @formatter:on */
  }

  @Test
  public final void testLtBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* @formatter:off */
    test("testLtBigInteger2", "Numbers.BigIntegers.lt(new BigInteger(\"100\")).test(new BigInteger(\"100\")) should be false")
    .when(() ->
      Numbers.BigIntegers.lt(new BigInteger("100"))
    )
    .then(actual ->
      assertThat(actual.test(number)).isFalse()
    );
    /* @formatter:on */
  }

  @Test
  public final void testLtBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.lt(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLtString() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.lt("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLtString2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.lt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLtString3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.lt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLeBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLeString() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeString2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeString3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.le("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqString() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqString2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqString3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.eq("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGtString() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtString2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtString3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.gt("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGeBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge(new BigInteger("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeString() {
    /* given */
    final BigInteger number = new BigInteger("99");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGeString2() {
    /* given */
    final BigInteger number = new BigInteger("100");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeString3() {
    /* given */
    final BigInteger number = new BigInteger("101");

    /* when */
    final Predicate<BigInteger> actual = Numbers.BigIntegers.ge("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testTotalCollectionOfBigInteger() {
    /* given */
    final List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"),
        new BigInteger("5"), new BigInteger("6"), new BigInteger("7"), new BigInteger("8"), new BigInteger("9"), new BigInteger("10"));
    final BigInteger expected = numbers.stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.total(numbers);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  class Something {
    private final BigInteger number;

    public Something(final String number) {
      this.number = new BigInteger(number);
    }

    public BigInteger getNumber() {
      return number;
    }
  }

  @Test
  public final void testTotalCollectionOfTFunctionOfTBigInteger() {
    /* given */
    final String number1 = "1";
    final String number2 = "2";
    final String number3 = "3";
    final String number4 = "4";
    final String number5 = "5";
    final String number6 = "6";
    final String number7 = "7";
    final String number8 = "8";
    final String number9 = "9";
    final String number10 = "10";
    final List<Something> numbers = Arrays.asList(new Something(number1), new Something(number2), new Something(number3), new Something(
        number4), new Something(number5), new Something(number6), new Something(number7), new Something(number8), new Something(number9),
        new Something(number10));
    final BigInteger expected = Arrays.asList(new BigInteger(number1), new BigInteger(number2), new BigInteger(number3),
        new BigInteger(number4), new BigInteger(number5), new BigInteger(number6), new BigInteger(number7), new BigInteger(number8),
        new BigInteger(number9), new BigInteger(number10))
        .stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.total(numbers, s -> s.getNumber());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testTotalCollectionOfTFunctionOfTBigInteger2() {
    /* given */
    final String number1 = "1";
    final String number2 = "2";
    final String number3 = "3";
    final String number4 = "4";
    final String number5 = "5";
    final String number6 = "6";
    final String number7 = "7";
    final String number8 = "8";
    final String number9 = "9";
    final String number10 = "10";
    final List<Something> numbers = Arrays.asList(new Something(number1), new Something(number2), new Something(number3), new Something(
        number4), new Something(number5), new Something(number6), new Something(number7), new Something(number8), new Something(number9),
        new Something(number10));
    final BigInteger expected = Arrays.asList(new BigInteger(number1), new BigInteger(number2), new BigInteger(number3),
        new BigInteger(number4), new BigInteger(number5), new BigInteger(number6), new BigInteger(number7), new BigInteger(number8),
        new BigInteger(number9), new BigInteger(number10))
        .stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.total(numbers, Something::getNumber);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfBigInteger() {
    /* given */
    final List<BigInteger> numbers = Arrays.asList(new BigInteger("1"), new BigInteger("2"), new BigInteger("3"), new BigInteger("4"),
        new BigInteger("5"), new BigInteger("6"), new BigInteger("7"), new BigInteger("8"), new BigInteger("9"), new BigInteger("10"));
    final BigInteger expected = numbers.stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.parallelTotal(numbers);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfTFunctionOfTBigInteger() {
    /* given */
    final String number1 = "1";
    final String number2 = "2";
    final String number3 = "3";
    final String number4 = "4";
    final String number5 = "5";
    final String number6 = "6";
    final String number7 = "7";
    final String number8 = "8";
    final String number9 = "9";
    final String number10 = "10";
    final List<Something> numbers = Arrays.asList(new Something(number1), new Something(number2), new Something(number3), new Something(
        number4), new Something(number5), new Something(number6), new Something(number7), new Something(number8), new Something(number9),
        new Something(number10));
    final BigInteger expected = Arrays.asList(new BigInteger(number1), new BigInteger(number2), new BigInteger(number3),
        new BigInteger(number4), new BigInteger(number5), new BigInteger(number6), new BigInteger(number7), new BigInteger(number8),
        new BigInteger(number9), new BigInteger(number10))
        .stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.parallelTotal(numbers, s -> s.getNumber());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfTFunctionOfTBigInteger2() {
    /* given */
    final String number1 = "1";
    final String number2 = "2";
    final String number3 = "3";
    final String number4 = "4";
    final String number5 = "5";
    final String number6 = "6";
    final String number7 = "7";
    final String number8 = "8";
    final String number9 = "9";
    final String number10 = "10";
    final List<Something> numbers = Arrays.asList(new Something(number1), new Something(number2), new Something(number3), new Something(
        number4), new Something(number5), new Something(number6), new Something(number7), new Something(number8), new Something(number9),
        new Something(number10));
    final BigInteger expected = Arrays.asList(new BigInteger(number1), new BigInteger(number2), new BigInteger(number3),
        new BigInteger(number4), new BigInteger(number5), new BigInteger(number6), new BigInteger(number7), new BigInteger(number8),
        new BigInteger(number9), new BigInteger(number10))
        .stream()
        .reduce(BigInteger.ZERO, BigInteger::add);

    /* when */
    final BigInteger actual = Numbers.BigIntegers.parallelTotal(numbers, Something::getNumber);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}
