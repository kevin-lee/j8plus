---
id: maybe
title: 'Maybe - A Better Optional'
sidebar_label: 'Maybe'
---

## Why `Maybe`
`Maybe` is an alternative to Java's `Optional`. Java has `Optional` then why do we need `Maybe`?

* First of all, Java's `Optional` is not `Serializable`. When it was added to Java 8, the designers of the language didn't want us to use it as a type for the object member variables.
* Java's `Optional` has the `get()` method which means it may throw an exception if `Optional.isPresent()` is `false`.


## Create
### `Maybe.maybe()`
To construct `Maybe`, use `Maybe.maybe`. If the given argument is `null`, it creates `Nothing`, otherwise it's `Just` with the given value.

```java
import j8plus.types.Maybe;

Maybe.maybe(123);
// Maybe<Integer> = Just(123)

Maybe.maybe(null);
// Maybe = Nothing
```


### `Maybe.nothing()`
```java
import j8plus.types.Maybe;

Maybe.nothing();
// Maybe = Nothing
```


### `Maybe` from `Optional`

```java
import j8plus.types.Maybe;

final Optional<String> optionalName = Optional.ofNullable("Kevin");
// Optional[Kevin]

// ...
final Maybe<String> maybeName = Maybe.fromOptional(optionalName);
// Just(Kevin)
```

### `Maybe` to `Optional`
If you need to convert `Maybe` into `Optional`, you can do it with `Maybe.toOptional()`.
```java
import j8plus.types.Maybe;

Maybe.maybe(1).toOptional() // Optional[1]
Maybe.nothing().toOptional() // Optional.empty
```


## Transform
### `Maybe.map()`

```java
import j8plus.types.Maybe;

Maybe.maybe(1).map(n -> n * 2) // Maybe<Integer> = Just(2)
```


### `Maybe.flatMap()`

```java
import j8plus.types.Maybe;

public Maybe<User> findUser(final Long id) 

// ...

final Maybe<User> maybeUser = findUser(1L);
// Maybe<User> = Just(User(1L, Kevin, Maybe<String> = Just(kevin@blah.blah)))
maybeUser.flatMap(user -> user.getEmail()) // Maybe<String> = Just(kevin@blah.blah)

final Maybe<User> maybeUser2 = findUser(2L);
// Maybe<User> = Just(User(2L, John, Maybe = Nothing))
maybeUser2.flatMap(user -> user.getEmail()) // Maybe = Nothing
```


### `Maybe.filter()`
`Maybe.filter()` can check if the value in `Maybe.Just` matches the given `Predicate` and if so, it keeps `Maybe.Just` as is. If not, it turns it into `Maybe.Nothing`.
If the `Maybe` is already `Maybe.Nothing`, nothing happens, and it is still `Maybe.Nothing`.

```java
import j8plus.types.Maybe;

Maybe.maybe(10).filter(n -> n > 9);
// Maybe<Integer> = Just(10)

Maybe.maybe(8).filter(n -> n > 9);
// Maybe = Nothing
```

## Get the Value
### `Maybe.fold()`
So if `Optional.get()` is dangerous because it throws `NoSuchElementException` when `Optional` has a `null` value (i.e. `Optional.empty()`), what should I use?

A better way is using `fold` which is also called as 'Catemorphism' in functional programming.

`Maybe.fold()` takes two parameters. The first one is a `Supplier` to get the alternative value when `Maybe` is `Nothing`. The other one is a `Function` to handle the value when it is `Maybe.Just`.

e.g.)
```java
import j8plus.types.Maybe;

Maybe.maybe(10)
  .fold(
    // Nothing case: When it's Nothing, returns 0.
    () -> 0,
    // Just case: When it's Just, return the value after applying this function.
    n -> n * 5
  ); // 50
```

```java
import j8plus.types.Maybe;

Maybe.maybe(1).fold(() -> 0, n -> n * 2); // 2
Maybe.nothing().fold(() -> 0, n -> n * 2); // 0

Maybe.maybe("Kevin").fold(() -> "", name -> "Hello " + name);
// "Hello Kevin"

Maybe.nothing().fold(() -> "", name -> "Hello " + name);
// ""
```

When it is `Maybe.Just`, if you don't want to change the value but just want to get it, you can use `Function.identity()` for the `Just` case function.

```java
import java.util.function.Function;
import j8plus.types.Maybe;

Maybe.maybe(123).fold(() -> 0, Function.identity()) // 123
Maybe.nothing().fold(() -> 0, Function.identity()) // 0

``` 

`Maybe.fold()` is the same as `Maybe.map().getOrElse()`.

```java
import j8plus.types.Maybe;

Maybe.maybe(1).fold(() -> 0, n -> n * 2); // 2
Maybe.maybe(1).map(n -> n * 2).getOrElse(() -> 0); // 2

Maybe.nothing().fold(() -> 0, n -> n * 2); // 0
Maybe.nothing().map(n -> n * 2).getOrElse(() -> 0); // 0
```


### `Maybe.getOrElse()`
If you don't need to change the value in the `Maybe` and just want to get it, you can use `Maybe.getOrElse()`.

```java
import j8plus.types.Maybe;

Maybe.maybe(1).getOrElse(() -> 0); // 1
Maybe.nothing().getOrElse(() -> 0); // 0
```


## Check Maybe Type
### `Maybe.isJust()`
To check if a `Maybe` instance is `Maybe.Just`, use `Maybe.isJust()`. If `true`, it is `Maybe.Just`. If `false`, it is `Maybe.Nothing`.

```java
import j8plus.types.Maybe;

Maybe.maybe(1).isJust() // true
Maybe.nothing().isJust() // false
```


### `Maybe.isNothing()`
To check if a `Maybe` instance is `Maybe.Nothing`, use `Maybe.isNothing()`. If `true`, it is `Maybe.Nothing`. If `false`, it is `Maybe.Just`.

```java
import j8plus.types.Maybe;

Maybe.nothing().isNothing() // true
Maybe.maybe(1).isNothing() // false
```
