package j8plus.types;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static testosterone.Testosterone.test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kevin Lee
 * @since 2019-09-26
 */
public class EitherTest {

  @Test
  public void left() {
    final String expectedValue = "Some error";
    test("Either.left", "Either.left should create Left")
      .when(() ->
        Either.<String, Integer>left(expectedValue)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void right() {
    final Integer expectedValue = 999;
    test("Either.right", "Either.right should create Right")
      .when(() ->
        Either.<String, Integer>right(expectedValue)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void fromOptional_NonEmptyCase() {
    final Integer expectedValue = 999;
    final Optional<Integer> input = Optional.ofNullable(expectedValue);
    test("Either.fromOptional(nonEmpty)", "Either.fromOptional(Optional(someValue)) should create Right")
      .when(() ->
        Either.fromOptional(input, () -> "Some error")
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void fromOptional_EmptyCase() {
    final String expectedValue =  "Some error";
    final Optional<Integer> input = Optional.empty();
    test("Either.fromOptional(empty)", "Either.fromOptional(Optional.empty()) should create Left")
      .when(() ->
        Either.fromOptional(input, () -> expectedValue)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void fromMaybe_JustCase() {
    final Integer expectedValue = 999;
    final Maybe<Integer> input = Maybe.just(expectedValue);
    test("Either.fromMaybe(just)", "Either.fromMaybe(Just(someValue)) should create Right")
      .when(() ->
        Either.fromMaybe(input, () -> "Some error")
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void fromMaybe_NothingCase() {
    final String expectedValue =  "Some error";
    final Maybe<Integer> input = Maybe.nothing();
    test("Either.fromMaybe(Nothing)", "Either.fromMaybe(Nothing) should create Left")
      .when(() ->
        Either.fromMaybe(input, () -> expectedValue)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void isLeft_LeftCase() {
    test("Either.isLeft", "Either.isLeft should return true for Left")
      .when(() ->
        Either.<String, Integer>left("")
      )
      .then(actual ->
        assertThat(actual.isLeft()).isTrue()
      );
  }

  @Test
  public void isLeft_RightCase() {
    test("Either.isLeft", "Either.isLeft should return false for Right")
      .when(() ->
        Either.<String, Integer>right(1)
      )
      .then(actual ->
        assertThat(actual.isLeft()).isFalse()
      );
  }

  @Test
  public void isRight_LeftCase() {
    test("Either.isRight", "Either.isRight should return false for Left")
      .when(() ->
        Either.<String, Integer>left("")
      )
      .then(actual ->
        assertThat(actual.isRight()).isFalse()
      );
  }

  @Test
  public void isRight_RightCase() {
    test("Either.isRight", "Either.isRight should return true for Right")
      .when(() ->
        Either.<String, Integer>right(1)
      )
      .then(actual ->
        assertThat(actual.isRight()).isTrue()
      );
  }

  @Test
  public void map_LeftCase() {
    final String expectedValue = "Some error";
    test("Either.map on Left", "Either.map on Left should just return itself")
      .when(() ->
        Either.<String, Integer>left(expectedValue).map(i -> i * 2)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void map_RightCase() {
    final Integer input = 999;
    final Integer additional = 111;
    final Integer expectedValue = input + additional;
    test("Either.map on Right", "Either.map on Right should apply the given function")
      .when(() ->
        Either.<String, Integer>right(input).map(i -> i + additional)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void flatMap_LeftCase() {
    final String expectedValue = "Some error";
    test("Either.flatMap on Left", "Either.flatMap on Left should just return itself")
      .when(() ->
        Either.<String, Integer>left(expectedValue)
          .flatMap(i -> Either.right(i * 2))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void flatMap_RightCase() {
    final Integer input = 999;
    final Integer additional = 111;
    final Integer expectedValue = input + additional;
    test("Either.flatMap on Right", "Either.flatMap on Right should apply the given function")
      .when(() ->
        Either.<String, Integer>right(input).flatMap(i -> Either.right(i + additional))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void leftMap_LeftCase() {
    final String input = "Some error";
    final String additional = " and something else";
    final String expectedValue = input + additional;
    test("Either.leftMap on Left", "Either.leftMap on Left should apply the given function")
      .when(() ->
        Either.<String, Integer>left(input).leftMap(s -> s + additional)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void leftMap_RightCase() {
    final Integer expectedValue = 999;
    test("Either.leftMap on Right", "Either.leftMap on Right should just return itself")
      .when(() ->
        Either.<String, Integer>right(expectedValue).leftMap(s -> s + "whatever")
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );

  }

  @Test
  public void leftFlatMap_LeftCase() {
    final String input = "Some error";
    final String additional = " and something else";
    final String expectedValue = input + additional;
    test("Either.leftFlatMap on Left", "Either.leftFlatMap on Left should apply the given function")
      .when(() ->
        Either.<String, Integer>left(input)
          .leftFlatMap(s -> Either.left(s + additional))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );

  }

  @Test
  public void leftFlatMap_RightCase() {
    final Integer expectedValue = 999;
    test("Either.leftFlatMap on Right", "Either.leftFlatMap on Right should just return itself")
      .when(() ->
        Either.<String, Integer>right(expectedValue)
          .leftFlatMap(s -> Either.left(s + "whatever"))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void swap_LeftCase() {
    final String expectedValue = "Some error";
    test("Either.swap on Left", "Either.swap on Left should return Right with the same data")
      .when(() ->
        Either.<String, Integer>left(expectedValue).swap()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void swap_RightCase() {
    final Integer expectedValue = 999;
    test("Either.map on Right", "Either.map on Right should apply the given function")
      .when(() ->
        Either.<String, Integer>right(expectedValue).swap()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void ap_LeftCase() {
    final String expectedValue = "Some error";
    test("Either.ap on Left", "Either.ap on Left should just return itself")
      .when(() ->
        Either.<String, Integer>left(expectedValue).ap(() -> Either.right(i -> i * 2))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.left(expectedValue))
      );
  }

  @Test
  public void ap_RightCase() {
    final Integer input = 999;
    final Integer additional = 111;
    final Integer expectedValue = input + additional;
    test("Either.ap on Right", "Either.ap on Left should return apply the function in the given Either")
      .when(() ->
        Either.<String, Integer>right(input).ap(() -> Either.right(i -> i + additional))
      )
      .then(actual ->
        assertThat(actual).isEqualTo(Either.right(expectedValue))
      );
  }

  @Test
  public void fold_LeftCase() {
    final String input = "Some error";
    final String additional = " and something else";
    final String expectedValue = input + additional;
    test("Either.fold on Left", "Either.fold on Left should apply the leftFunction")
      .when(() ->
        Either.<String, Integer>left(input)
      )
      .then(actual ->
        assertThat(actual.isLeft()).isTrue()
      )
      .then(actual ->
        assertThat(actual.<String>fold(s -> s + additional, null)).isEqualTo(expectedValue)
      );
  }

  @Test
  public void fold_RightCase() {
    final Integer input = 999;
    final Integer additional = 111;
    final String expectedValue = String.valueOf(input + additional);
    test("Either.fold on Right", "Either.fold on Right should apply the rightFunction")
      .when(() ->
        Either.<String, Integer>right(input)
      )
      .then(actual ->
        assertThat(actual.isRight()).isTrue()
      )
      .then(actual ->
        assertThat(actual.<String>fold(null, i -> String.valueOf(i + additional))).isEqualTo(expectedValue)
      );

  }

  @Test
  public void forEach_LeftCase() {
    final String input = "Some error";
    final Integer[] updated = { null };
    test("Either.forEach on Left", "Either.forEach on Left should do nothing")
      .when(() ->
        Either.<String, Integer>left(input)
          .forEach(i -> updated[0] = i)
      )
      .then(() ->
        assertThat(updated[0]).isNull()
      );
  }

  @Test
  public void forEach_RightCase() {
    final Integer input = 999;
    final Integer additional = 111;
    final Integer expectedValue = input + additional;
    final Integer[] updated = { null };
    test("Either.forEach on Right", "Either.forEach on Right should apply the given function with side-effect")
      .when(() ->
        Either.<String, Integer>right(input)
          .forEach(i -> updated[0] = i + additional)
      )
      .then(() ->
        assertThat(updated[0]).isEqualTo(expectedValue)
      );
  }

  @Test
  public void hashCode_LeftCase() {
    final String input = "Some error";
    final int expected = Objects.hashCode(input);
    test("Either.hashCode on Left", "Either.hashCode on Left should return the hashCode of Left data")
      .when(() ->
        Either.<String, Integer>left(input).hashCode()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(expected)
      );
  }

  @Test
  public void hashCode_RightCase() {
    final Integer input = 999;
    final int expected = Objects.hashCode(input);
    test("Either.hashCode on Right", "Either.hashCode on Right should return the hashCode of Right data")
      .when(() ->
        Either.<String, Integer>right(input).hashCode()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(expected)
      );
  }

  @Test
  public void toString_LeftCase() {
    final String input = "Some error";
    final String expected = "Left(" + input + ")";
    test("Either.toString on Left", "Either.toString on Left should return \"Left(data)\"")
      .when(() ->
        Either.<String, Integer>left(input).toString()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(expected)
      );
  }

  @Test
  public void toString_RightCase() {
    final Integer input = 999;
    final String expected = "Right(" + input + ")";
    test("Either.toString on Right", "Either.toString on Right should return Right(\"data\")")
      .when(() ->
        Either.<String, Integer>right(input).toString()
      )
      .then(actual ->
        assertThat(actual).isEqualTo(expected)
      );
  }

}