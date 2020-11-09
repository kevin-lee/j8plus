---
id: maybe
title: 'Maybe - A Better Optional'
sidebar_label: 'Maybe (WIP)'
---

## Why `Maybe`
`Maybe` is an alternative to Java's `Optional`. Java has `Optional` then why do we need `Maybe`?

* First of all, Java's `Optional` is not `Serializable`. When it was added to Java 8, the designers of the language didn't want us to use it as a type for the object member variables.
* Java's `Optional` has the `get()` method which means it may throw an exception if `Optional.isPresent()` is `false`.

## Use `Maybe`
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
### Use the value - `Maybe.fold()`
### `Maybe.isJust()`
### `Maybe.isNothing()`

## Create `Maybe` from `Optional`

```java
import j8plus.types.Maybe;

final Optional<String> optionalName = Optional.ofNullable("Kevin");
// Optional[Kevin]

// ...
final Maybe<String> maybeName = Maybe.fromOptional(optionalName);
// Just(Kevin)
```

## `Maybe` to `Optional`
If you need to convert `Maybe` into `Optional`, you can do it with `Maybe.toOptional()`.
```java
import j8plus.types.Maybe;

Maybe.maybe(1).toOptional() // Optional[1]
Maybe.nothing().toOptional() // Optional.empty
```