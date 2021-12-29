---
id: either
title: 'Either - Turn Your Partial Function into a Total Function'
sidebar_label: 'Either (WIP)'
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
// Either<String, Integer> = Either.Right(1)
```


### Either.right to contain the error
```java
import j8plus.types.Either;

Either.<String, Integer>left("Error message");
// Either<String, Integer> = Either.Left("Error message")
```

### `Either` from `Optional`
```java
import j8plus.types.Either;

final Optional<Integer> num = Optional.ofNullable(1);
Either.fromOptional(num, () -> "No number found");
// Either<String, Integer> = Either.Right(1)

final Optional<Integer> noNum = Optional.empty();
Either.fromOptional(noNum, () -> "No number found");
// Either<String, Integer> = Either.Left("No number found")
```

### `Either` from `Maybe`
```java
import j8plus.types.Maybe;
import j8plus.types.Either;

final Maybe<Integer> num = Maybe.maybe(1);
Either.fromMaybe(num, () -> "No number found");
// Either<String, Integer> = Either.Right(1)

final Maybe<Integer> noNum = Maybe.nothing();
Either.fromMaybe(noNum, () -> "No number found");
// Either<String, Integer> = Either.Left("No number found")
```


## Transform
### `Either.map`
```java
import j8plus.types.Either;

final Either<String, Integer> errorOrNumber = Either.right(5);
// Either<String, Integer> = Either.Right(5)

errorOrNumber.map(n -> n * 2);
// Either<String, Integer> = Either.Right(10)
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

### `Either.swap`



## Get the Value

### `Either.fold`


## Check Value in Either

### `Either.isRight`

### `Either.isLeft`


### Example
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
