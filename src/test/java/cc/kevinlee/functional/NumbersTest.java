package cc.kevinlee.functional;

import static kevinlee.testosterone.Testosterone.test;
import static kevinlee.testosterone.Testosterone.throwing;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.elixirian.kommonlee.test.CommonTestHelper;
import org.junit.jupiter.api.Test;

import cc.kevinlee.functional.Numbers.BigDecimalNumber;
import cc.kevinlee.functional.Numbers.BigIntegerNumber;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-06-20)
 *
 */
public class NumbersTest {

  @Test
  public final void testNumbers() {
    /* @formatter:off */
    test("test Numbers()", "Numbers() must be private")
      .when(() ->
        CommonTestHelper.newConstructorTester(Numbers.class, this).mustBePrivate().test()
      )
      .expect(throwing(IllegalAccessException.class));
    /* @formatter:on */

  }

  @Test
  public final void testIsOddInt() {
    /* given */
    final int number = 0;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddInt1() {
    /* given */
    final int number = 1;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddInt2() {
    /* given */
    final int number = 2;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddInt3() {
    /* given */
    final int number = 3;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddInt4() {
    /* given */
    final int number = 10;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddInt5() {
    /* given */
    final int number = 11;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddInt6() {
    /* given */
    final int number = -1;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddInt7() {
    /* given */
    final int number = -2;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenInt() {
    /* given */
    final int number = 0;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenInt1() {
    /* given */
    final int number = 1;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenInt2() {
    /* given */
    final int number = 2;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenInt3() {
    /* given */
    final int number = 3;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenInt4() {
    /* given */
    final int number = 10;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenInt5() {
    /* given */
    final int number = 11;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenInt6() {
    /* given */
    final int number = -1;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenInt7() {
    /* given */
    final int number = -2;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddLong() {
    /* given */
    final long number = 0L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddLong1() {
    /* given */
    final long number = 1L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddLong2() {
    /* given */
    final long number = 2L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddLong3() {
    /* given */
    final long number = 3L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddLong4() {
    /* given */
    final long number = 10L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddLong5() {
    /* given */
    final long number = 11L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddLong6() {
    /* given */
    final long number = -1L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddLong7() {
    /* given */
    final long number = -2L;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenLong() {
    /* given */
    final long number = 0L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenLong1() {
    /* given */
    final long number = 1L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenLong2() {
    /* given */
    final long number = 2L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenLong3() {
    /* given */
    final long number = 3L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenLong4() {
    /* given */
    final long number = 10L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenLong5() {
    /* given */
    final long number = 11L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenLong6() {
    /* given */
    final long number = -1L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenLong7() {
    /* given */
    final long number = -2L;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddBigInteger() {
    /* given */
    final BigInteger number = BigInteger.ZERO;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddBigInteger1() {
    /* given */
    final BigInteger number = BigInteger.ONE;

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("3");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddBigInteger4() {
    /* given */
    final BigInteger number = new BigInteger("10");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsOddBigInteger5() {
    /* given */
    final BigInteger number = new BigInteger("11");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddBigInteger6() {
    /* given */
    final BigInteger number = new BigInteger("-1");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsOddBigInteger7() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isOdd(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenBigInteger() {
    /* given */
    final BigInteger number = BigInteger.ZERO;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenBigInteger1() {
    /* given */
    final BigInteger number = BigInteger.ONE;

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("3");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenBigInteger4() {
    /* given */
    final BigInteger number = new BigInteger("10");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsEvenBigInteger5() {
    /* given */
    final BigInteger number = new BigInteger("11");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenBigInteger6() {
    /* given */
    final BigInteger number = new BigInteger("-1");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsEvenBigInteger7() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isEven(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeInt() {
    /* given */
    final int number = -10;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeInt1() {
    /* given */
    final int number = -5;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeInt2() {
    /* given */
    final int number = -2;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeInt3() {
    /* given */
    final int number = -1;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeInt4() {
    /* given */
    final int number = 0;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeInt5() {
    /* given */
    final int number = 1;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeInt6() {
    /* given */
    final int number = 2;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeInt7() {
    /* given */
    final int number = 5;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeInt8() {
    /* given */
    final int number = 10;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt() {
    /* given */
    final int number = -10;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt1() {
    /* given */
    final int number = -5;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt2() {
    /* given */
    final int number = -2;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt3() {
    /* given */
    final int number = -1;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt4() {
    /* given */
    final int number = 0;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsZeroInt5() {
    /* given */
    final int number = 1;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt6() {
    /* given */
    final int number = 2;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt7() {
    /* given */
    final int number = 5;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroInt8() {
    /* given */
    final int number = 10;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt() {
    /* given */
    final int number = -10;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt1() {
    /* given */
    final int number = -5;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt2() {
    /* given */
    final int number = -2;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt3() {
    /* given */
    final int number = -1;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt4() {
    /* given */
    final int number = 0;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveInt5() {
    /* given */
    final int number = 1;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveInt6() {
    /* given */
    final int number = 2;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveInt7() {
    /* given */
    final int number = 5;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveInt8() {
    /* given */
    final int number = 10;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeLong() {
    /* given */
    final long number = -10;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeLong1() {
    /* given */
    final long number = -5;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeLong2() {
    /* given */
    final long number = -2;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeLong3() {
    /* given */
    final long number = -1;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeLong4() {
    /* given */
    final long number = 0;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeLong5() {
    /* given */
    final long number = 1;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeLong6() {
    /* given */
    final long number = 2;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeLong7() {
    /* given */
    final long number = 5;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeLong8() {
    /* given */
    final long number = 10;

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong() {
    /* given */
    final long number = -10;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong1() {
    /* given */
    final long number = -5;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong2() {
    /* given */
    final long number = -2;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong3() {
    /* given */
    final long number = -1;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong4() {
    /* given */
    final long number = 0;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsZeroLong5() {
    /* given */
    final long number = 1;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong6() {
    /* given */
    final long number = 2;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong7() {
    /* given */
    final long number = 5;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroLong8() {
    /* given */
    final long number = 10;

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong() {
    /* given */
    final long number = -10;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong1() {
    /* given */
    final long number = -5;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong2() {
    /* given */
    final long number = -2;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong3() {
    /* given */
    final long number = -1;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong4() {
    /* given */
    final long number = 0;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveLong5() {
    /* given */
    final long number = 1;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveLong6() {
    /* given */
    final long number = 2;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveLong7() {
    /* given */
    final long number = 5;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveLong8() {
    /* given */
    final long number = 10;

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("-10");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigInteger1() {
    /* given */
    final BigInteger number = new BigInteger("-5");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("-2");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("-1");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigInteger4() {
    /* given */
    final BigInteger number = new BigInteger("0");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigInteger5() {
    /* given */
    final BigInteger number = new BigInteger("1");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigInteger6() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigInteger7() {
    /* given */
    final BigInteger number = new BigInteger("5");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigInteger8() {
    /* given */
    final BigInteger number = new BigInteger("10");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("-10");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger1() {
    /* given */
    final BigInteger number = new BigInteger("-5");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("-2");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("-1");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger4() {
    /* given */
    final BigInteger number = new BigInteger("0");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsZeroBigInteger5() {
    /* given */
    final BigInteger number = new BigInteger("1");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger6() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger7() {
    /* given */
    final BigInteger number = new BigInteger("5");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigInteger8() {
    /* given */
    final BigInteger number = new BigInteger("10");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("-10");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger1() {
    /* given */
    final BigInteger number = new BigInteger("-5");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("-2");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger3() {
    /* given */
    final BigInteger number = new BigInteger("-1");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger4() {
    /* given */
    final BigInteger number = new BigInteger("0");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigInteger5() {
    /* given */
    final BigInteger number = new BigInteger("1");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigInteger6() {
    /* given */
    final BigInteger number = new BigInteger("2");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigInteger7() {
    /* given */
    final BigInteger number = new BigInteger("5");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigInteger8() {
    /* given */
    final BigInteger number = new BigInteger("10");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("-10");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigDecimal1() {
    /* given */
    final BigDecimal number = new BigDecimal("-5");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("-2");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("-1");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsNegativeBigDecimal4() {
    /* given */
    final BigDecimal number = new BigDecimal("0");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigDecimal5() {
    /* given */
    final BigDecimal number = new BigDecimal("1");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigDecimal6() {
    /* given */
    final BigDecimal number = new BigDecimal("2");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigDecimal7() {
    /* given */
    final BigDecimal number = new BigDecimal("5");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsNegativeBigDecimal8() {
    /* given */
    final BigDecimal number = new BigDecimal("10");

    /* when */
    final boolean actual = Numbers.isNegative(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("-10");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal1() {
    /* given */
    final BigDecimal number = new BigDecimal("-5");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("-2");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("-1");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal4() {
    /* given */
    final BigDecimal number = new BigDecimal("0");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsZeroBigDecimal5() {
    /* given */
    final BigDecimal number = new BigDecimal("1");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal6() {
    /* given */
    final BigDecimal number = new BigDecimal("2");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal7() {
    /* given */
    final BigDecimal number = new BigDecimal("5");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsZeroBigDecimal8() {
    /* given */
    final BigDecimal number = new BigDecimal("10");

    /* when */
    final boolean actual = Numbers.isZero(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("-10");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal1() {
    /* given */
    final BigDecimal number = new BigDecimal("-5");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("-2");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("-1");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal4() {
    /* given */
    final BigDecimal number = new BigDecimal("0");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public final void testIsPositiveBigDecimal5() {
    /* given */
    final BigDecimal number = new BigDecimal("1");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigDecimal6() {
    /* given */
    final BigDecimal number = new BigDecimal("2");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigDecimal7() {
    /* given */
    final BigDecimal number = new BigDecimal("5");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testIsPositiveBigDecimal8() {
    /* given */
    final BigDecimal number = new BigDecimal("10");

    /* when */
    final boolean actual = Numbers.isPositive(number);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testBigIntBigInteger() {
    /* given */
    final BigInteger number = new BigInteger("1");
    final BigIntegerNumber expected = new BigIntegerNumber(new BigInteger("1"));

    /* when */
    final BigIntegerNumber actual = Numbers.bigInt(number);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testBigIntBigInteger2() {
    /* given */
    final BigInteger number = new BigInteger("1");
    final BigIntegerNumber expected = new BigIntegerNumber(new BigInteger("0"));

    /* when */
    final BigIntegerNumber actual = Numbers.bigInt(number);

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testBigIntString() {
    /* given */
    final String number = "1";
    final BigIntegerNumber expected = new BigIntegerNumber(new BigInteger(number));

    /* when */
    final BigIntegerNumber actual = Numbers.bigInt(number);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testBigIntString2() {
    /* given */
    final String number = "1";
    final BigIntegerNumber expected = new BigIntegerNumber(new BigInteger("0"));

    /* when */
    final BigIntegerNumber actual = Numbers.bigInt(number);

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testDecimalBigDecimal() {
    /* given */
    final String number = "1";
    final BigDecimalNumber expected = new BigDecimalNumber(new BigDecimal(number));

    /* when */
    final BigDecimalNumber actual = Numbers.decimal(new BigDecimal(number));

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDecimalBigDecimal2() {
    /* given */
    final String number = "1";
    final BigDecimalNumber expected = new BigDecimalNumber(new BigDecimal("0"));

    /* when */
    final BigDecimalNumber actual = Numbers.decimal(new BigDecimal(number));

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testDecimalString() {
    /* given */
    final String number = "1";
    final BigDecimalNumber expected = new BigDecimalNumber(new BigDecimal(number));

    /* when */
    final BigDecimalNumber actual = Numbers.decimal(number);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testDecimalString2() {
    /* given */
    final String number = "1";
    final BigDecimalNumber expected = new BigDecimalNumber(new BigDecimal("0"));

    /* when */
    final BigDecimalNumber actual = Numbers.decimal(number);

    /* then */
    assertThat(actual).isNotEqualTo(expected);
  }

  @Test
  public final void testLtBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLtBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLtBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLtString() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLtString2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLtString3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.lt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLeBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testLeString() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeString2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testLeString3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.le("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqBigDecimal2_2() {
    /* given */
    final BigDecimal number = new BigDecimal("100.0");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqString() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testEqString2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqString2_2() {
    /* given */
    final BigDecimal number = new BigDecimal("100.0");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testEqString3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.eq("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGtString() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtString2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGtString3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.gt("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }



  @Test
  public final void testGeBigDecimal() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGeBigDecimal2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeBigDecimal3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge(new BigDecimal("100"));

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeString() {
    /* given */
    final BigDecimal number = new BigDecimal("99.99");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge("100");

    /* then */
    assertThat(actual.test(number)).isFalse();
  }

  @Test
  public final void testGeString2() {
    /* given */
    final BigDecimal number = new BigDecimal("100");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }

  @Test
  public final void testGeString3() {
    /* given */
    final BigDecimal number = new BigDecimal("100.00001");

    /* when */
    final Predicate<BigDecimal> actual = Numbers.ge("100");

    /* then */
    assertThat(actual.test(number)).isTrue();
  }


  @Test
  public final void testTotalCollectionOfBigDecimal() {
    /* given */
    final List<BigDecimal> numbers = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("4"),
        new BigDecimal("5"), new BigDecimal("6"), new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9"), new BigDecimal("10"));
    final BigDecimal expected = numbers.stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.total(numbers);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  class Something {
    private final BigDecimal number;

    public Something(final String number) {
      this.number = new BigDecimal(number);
    }

    public BigDecimal getNumber() {
      return number;
    }
  }

  @Test
  public final void testTotalCollectionOfTFunctionOfTBigDecimal() {
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
    final BigDecimal expected = Arrays.asList(new BigDecimal(number1), new BigDecimal(number2), new BigDecimal(number3),
        new BigDecimal(number4), new BigDecimal(number5), new BigDecimal(number6), new BigDecimal(number7), new BigDecimal(number8),
        new BigDecimal(number9), new BigDecimal(number10))
        .stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.total(numbers, s -> s.getNumber());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testTotalCollectionOfTFunctionOfTBigDecimal2() {
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
    final BigDecimal expected = Arrays.asList(new BigDecimal(number1), new BigDecimal(number2), new BigDecimal(number3),
        new BigDecimal(number4), new BigDecimal(number5), new BigDecimal(number6), new BigDecimal(number7), new BigDecimal(number8),
        new BigDecimal(number9), new BigDecimal(number10))
        .stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.total(numbers, Something::getNumber);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfBigDecimal() {
    /* given */
    final List<BigDecimal> numbers = Arrays.asList(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("4"),
        new BigDecimal("5"), new BigDecimal("6"), new BigDecimal("7"), new BigDecimal("8"), new BigDecimal("9"), new BigDecimal("10"));
    final BigDecimal expected = numbers.stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.parallelTotal(numbers);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfTFunctionOfTBigDecimal() {
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
    final BigDecimal expected = Arrays.asList(new BigDecimal(number1), new BigDecimal(number2), new BigDecimal(number3),
        new BigDecimal(number4), new BigDecimal(number5), new BigDecimal(number6), new BigDecimal(number7), new BigDecimal(number8),
        new BigDecimal(number9), new BigDecimal(number10))
        .stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.parallelTotal(numbers, s -> s.getNumber());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testParallelTotalCollectionOfTFunctionOfTBigDecimal2() {
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
    final BigDecimal expected = Arrays.asList(new BigDecimal(number1), new BigDecimal(number2), new BigDecimal(number3),
        new BigDecimal(number4), new BigDecimal(number5), new BigDecimal(number6), new BigDecimal(number7), new BigDecimal(number8),
        new BigDecimal(number9), new BigDecimal(number10))
        .stream()
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    /* when */
    final BigDecimal actual = Numbers.parallelTotal(numbers, Something::getNumber);

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}
