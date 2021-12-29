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
// Either<String, Integer> = Either.Right(1)
```

### `Either` from `Optional`

### `Either` from `Maybe`


## Transform
### `Either.map`

### `Either.flatMap`

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
