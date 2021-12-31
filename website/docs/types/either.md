---
id: either
title: 'Either - Turn Your Partial Function into a Total Function'
sidebar_label: 'Either'
---
## Why `Either`
`Either` can turn a partial function into a total function.

When your function (or method) is a partial function meaning that it may not have a return value for some inputs, your code becomes hard to understand and maintain because it may throw an `Exception` when there's no corresponding result for a such input. Throwing an `Exception` means that there might be any number exceptions that can be thrown from the function, and to see what might be thrown, you have to check the implementation details. 

To make it a total function so that it always has the output for the given input, there can be multiple ways but an easy and recommended way is using `Either`.

:::caution NOTE
`Either` is right-biased meaning that the most well-known combinators on `Either` (e.g. `map`, `flatMap`, etc.) are by default for the `Right` value. If you want to manipulate the left one, you need to use the combinators for `Left` which are usually prefixed with `left` (e.g. `leftMap`, `leftFlatMap`, etc.).  
:::

## Create

### Either.right to contain the result
```java
import j8plus.types.Either;

Either.<String, Integer>right(1);
// Either<String, Integer> = Right(1)
```


### Either.right to contain the error
```java
import j8plus.types.Either;

Either.<String, Integer>left("Error message");
// Either<String, Integer> = Left("Error message")
```

### `Either` from `Optional`
```java
import j8plus.types.Either;

final Optional<Integer> num = Optional.ofNullable(1);
Either.fromOptional(num, () -> "No number found");
// Either<String, Integer> = Right(1)

final Optional<Integer> noNum = Optional.empty();
Either.fromOptional(noNum, () -> "No number found");
// Either<String, Integer> = Left("No number found")
```

### `Either` from `Maybe`
```java
import j8plus.types.Maybe;
import j8plus.types.Either;

final Maybe<Integer> num = Maybe.maybe(1);
Either.fromMaybe(num, () -> "No number found");
// Either<String, Integer> = Right(1)

final Maybe<Integer> noNum = Maybe.nothing();
Either.fromMaybe(noNum, () -> "No number found");
// Either<String, Integer> = Left("No number found")
```


## Transform
### `Either.map`
```java
import j8plus.types.Either;

final Either<String, Integer> errorOrNumber = Either.right(5);
// Either<String, Integer> = Right(5)

errorOrNumber.map(n -> n * 2);
// Either<String, Integer> = Right(10)
```

### `Either.flatMap`
```java
import j8plus.types.Either;

public Either<String, Integer> foo(int n) {
  if (n < 0) {
    return Either.left(
      "foo can't take a negative int. [n: " + n + "]"
    );
  } else {
    return Either.right(n * 2);
  }
}

public Either<String, Integer> bar(int n) {
  if (n < 100) {
    return Either.left(
      "bar can't take an int less than 100. [n: " + n + "]"
    );
  } else {
    return Either.right(n - 100);
  }
}
```

All happy paths
```java
Either.<String, Integer>right(50) // Either<String, Integer> = Right(50)
  .flatMap(n -> foo(n))           // Either<String, Integer> = Right(100)
  .flatMap(n -> bar(n))           // Either<String, Integer> = Right(0)
// Or with method references
Either.<String, Integer>right(50) // Either<String, Integer> = Right(50)
  .flatMap(this::foo)             // Either<String, Integer> = Right(100)
  .flatMap(this::bar)             // Either<String, Integer> = Right(0)
```

Unhappy path cases
```java
Either.<String, Integer>right(-1) // Either<String, Integer> = Right(-1)
  .flatMap(n -> foo(n))           // Either<String, Integer> = Left("foo can't take a negative int. [n: -1]")
  .flatMap(n -> bar(n));          // Either<String, Integer> = Left("foo can't take a negative int. [n: -1]")
 
// Or with method references
Either.<String, Integer>right(-1) // Either<String, Integer> = Right(-1)
  .flatMap(this::foo)             // Either<String, Integer> = Left("foo can't take a negative int. [n: -1]")
  .flatMap(this::bar);            // Either<String, Integer> = Left("foo can't take a negative int. [n: -1]")
```

```java
Either.<String, Integer>right(5) // Either<String, Integer> = Right(5)
  .flatMap(n -> foo(n))          // Either<String, Integer> = Right(10)
  .flatMap(n -> bar(n));         // Either<String, Integer> = Left("bar can't take an int less than 100. [n: 10]")
 
// Or with method references
Either.<String, Integer>right(5) // Either<String, Integer> = Right(5)
  .flatMap(this::foo)            // Either<String, Integer> = Right(10)
  .flatMap(this::bar);           // Either<String, Integer> = Left("bar can't take an int less than 100. [n: 10]")
```

### `Either.leftMap`
If you want to change the left value, you can use `map` to do it.

```java
import j8plus.types.Either;

public class MyError {
  public final String message;
  public MyError(final String message) {
    this.message = message;
  }
  
  @Override
  public String toString() {
    return "MyError(message=" + message + ")";
  }
}

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.leftMap(err -> new MyError(err));
// Either<MyError, Integer> = Left(MyError(message=Error message))
```

### `Either.leftFlatMap`
Would you like to place the `Left` value with another `Either` which can be another `Left` or even `Right`? You can use `leftFlatMap`.

`Left` to `Right`
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.leftFlatMap(err -> Either.right(0));
// Either<String, Integer> = Right(0)
```

Or it can be another `Left` like
```java
import j8plus.types.Either;

public class MyError {
  public final String message;
  public MyError(final String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "MyError(message=" + message + ")";
  }
}

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.leftFlatMap(err -> Either.left(new MyError(err)));
// Either<MyError, Integer> = Left(MyError(message=Error message))
```

### `Either.swap`
If you want to swap `Left` and `Right`? You can use `Either.swap()`.
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(1);
// Either<String, Integer> = Right(1)

errorMsgOrNum.swap();
// Either<Integer, String> = Left(1)
```
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.swap();
// Either<Integer, String> = Right(Error message)
```


## Get the Value

### `Either.fold`
If you want to get the value inside `Either`, you can use `Either.fold`. Since you can have only one result type, you have to provide ways to turn a type in `Right` and a type in `Left` into one result type. So `fold` takes two functions. One for the `Right` case and the other for the `Left` case.

The `Right` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(999);
// Either<String, Integer> = Right(999)

errorMsgOrNum.fold(err -> 0, n -> n);
// Integer = 999

// Or using the identity function from java.util.function.Function
import java.util.function.Function;

errorMsgOrNum.fold(err -> 0, Function.identity());
// Integer = 999
```

The `Left` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.fold(err -> 0, n -> n);
// Integer = 0
```

You can also turn the value into a completely different type.
```java
import j8plus.types.Either;
import java.time.Instant;

final Either<String, Long> errorMsgOrMillis = Either.right(1638316800000L);
// Either<String, Long> = Right(1638316800000L)

errorMsgOrMillis.fold(err -> Instant.now(), millis -> Instant.ofEpochMilli(millis));
// Instant = 2021-12-01T00:00:00Z

// Or using method reference  
errorMsgOrMillis.fold(err -> Instant.now(), Instant::ofEpochMilli);
// Instant = 2021-12-01T00:00:00Z
```

```java
import j8plus.types.Either;
import java.time.Instant;

final Either<String, Long> errorMsgOrMillis = Either.left("Error message");
// Either<String, Long> = Left(Error message)

errorMsgOrMillis.fold(err -> Instant.now(), millis -> Instant.ofEpochMilli(millis));
// Instant = 2021-12-30T09:15:24.033117Z
```

### `Either.getOrElse`
To get the `Right` value, you can use `getOrElse(Supplier<AlternativeValue>)`. If the `Either` is `Left`, it returns the given alternative from the supplier param.

`Right` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(999);
// Either<String, Integer> = Right(999)

errorMsgOrNum.getOrElse(() -> 0);
// Integer = 999
```

`Left` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.getOrElse(() -> 0);
// Integer = 0
```

### `Either.getLeftOrElse`
To get the `Left` value, you can use `getLeftOrElse(Supplier<AlternativeValue>)`. If the `Either` is `Right`, it returns the given alternative from the supplier param.

`Right` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(999);
// Either<String, Integer> = Right(999)

errorMsgOrNum.getLeftOrElse(() -> "Default");
// String = Default
```

`Left` case,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum.getLeftOrElse(() -> "Default");
// String = Error message
```

## Check Value in Either

If you want to chekc if `Either` is `Right` or `Left`, you can use `isRight()` and `isLeft()` respectively.

### `Either.isRight`
To check if the `Either` is `Right`, use `isRight()`,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(999);
// Either<String, Integer> = Right(999)

errorMsgOrNum.isRight();
// boolean = true

final Either<String, Integer> errorMsgOrNum2 = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum2.isRight();
// boolean = false
```

### `Either.isLeft`
To check if the `Either` is `Left`, use `isLeft()`,
```java
import j8plus.types.Either;

final Either<String, Integer> errorMsgOrNum = Either.right(999);
// Either<String, Integer> = Right(999)

errorMsgOrNum.isLeft();
// boolean = false

final Either<String, Integer> errorMsgOrNum2 = Either.left("Error message");
// Either<String, Integer> = Left(Error message)

errorMsgOrNum2.isLeft();
// boolean = true
```


## Example
```java
import j8plus.types.Either;

public class DivisionByZeroError {
  public final int dividend;
  
  public DivisionByZeroError(final int dividend) {
    this.dividend = dividend;
  }
  
  public String getMessage() {
    return "Arithmetic error: dividing " + dividend + " by zero is not possible.";
  }

  @Override
  public String toString() {
    return new StringBuilder("DivisionByZeroError(")
      .append("dividend=").append(dividend)
      .append(')')
      .toString();
  }
}

public Either<DivisionByZeroError, Integer> divide(int a, int b) {
  if (b == 0) {
    return Either.left(new DivisionByZeroError(a));
  } else {
    return Either.right(a / b);
  }
}

final var result1 = divide(10, 2);
// Either<DivisionByZeroError, Integer> = Right(5)

final var result2 = divide(10, 0);
// Either<DivisionByZeroError, Integer> = Left(DivisionByZeroError(dividend=10))
```
