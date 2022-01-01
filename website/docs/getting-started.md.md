---
id: getting-started
title: 'J8+ - Getting Started'
sidebar_label: Getting Started
slug: /
---

## ![](/img/j8plus-logo-64x64.png) J8+
J8+ (J8Plus) - The Missing Functional Parts of Java 8

[![Build Status](https://github.com/Kevin-Lee/j8plus/workflows/Build/badge.svg)](https://github.com/Kevin-Lee/j8plus/actions?workflow=Build)
[![Release Status](https://github.com/Kevin-Lee/j8plus/workflows/Release/badge.svg)](https://github.com/Kevin-Lee/j8plus/actions?workflow=Release)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.kevinlee/j8plus/badge.svg)](https://search.maven.org/artifact/io.kevinlee/j8plus)

[![Coverage Status](https://coveralls.io/repos/Kevin-Lee/j8plus/badge.svg)](https://coveralls.io/r/Kevin-Lee/j8plus)
[![codecov](https://codecov.io/gh/Kevin-Lee/j8plus/branch/main/graph/badge.svg?token=ljEFnSj73n)](https://codecov.io/gh/Kevin-Lee/j8plus)


## Get J8+

### Maven

```xml
<dependencies>

  <dependency>
    <groupId>io.kevinlee</groupId>
    <artifactId>j8plus</artifactId>
    <version>1.1.0</version>
  </dependency>

</dependencies>
```

### Gradle
* Add Dependency

```gradle
compile group: 'io.kevinlee', name: 'j8plus', version: '1.1.0'
```
  OR

```gradle
compile "io.kevinlee:testosterone:1.1.0"
```


## Why J8+?
Q: Java 8 comes with newly added functional features like Lambda Expressions and Method References so why do we need this library claiming that it contains some functional features that Java 8 does not have?

A: Java 8 has nicely implemented Lambda Expressions and Method References which meke all the existing libraries really useful and reusable, yet it still lacks some useful features that can make our lives a lot easiler. J8+ provides those missing functional features to make your life easier.

### Problem Examples and Solutions using J8+

#### Java - Recursion

Java 8's JVM does still not support [tail call optimization](http://en.wikipedia.org/wiki/Tail_call). So if you write a recursive method like the following one, you still get [StackOverflowError](http://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html).

```java
public static BigInteger factorial(final BigInteger acc, final BigInteger n) {
  if (n.equals(BigInteger.ONE)) {
    return acc;
  } else {
    return factorial(acc.multiply(n), n.subtract(BigInteger.ONE));
  }
}
```

#### J8+ - Recursion
[StackOverflowError](http://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html) when using recursive method can be solved using Trampoline provided in J8+. It requires only a little changes in your code.

```java
public static TailCallable<BigInteger> factorial(
    final BigInteger acc
  , final BigInteger n
) {
  if (n.equals(BigInteger.ONE)) {
    return done(acc);
  } else {
    return () -> factorial(acc.multiply(n), n.subtract(BigInteger.ONE));
  }
}
```
Now, you don't need to worry about StackOverflowError.

#### Java - Opposite Operation

Another example is Stream in Java 8's collections. The Stream has a useful method called filter, yet, unlike Scala, it does not have a method like filterNot which does the opposite operation. Let's say you need to get all non empty String from the list. You do something like this using functional features in Java 8.

```java
List<String> nonEmptyStrings =
    list.stream()
      .filter(text -> !text.isEmpty())
      .collect(toList())

```

It can be even more concise with Method References, but unfortunate, you can't use it as Stream does not have the opposite operation method of filter.  So if it's taking all empty Strings you do like

```java
List<String> nonEmptyStrings =
    list.stream()
      .filter(String::isEmpty) // no filterNot method in Stream
      .collect(toList())

```
But you can't use a method reference to filter out the empty String.

#### J8+ - Opposite Operation

Opposite operation when using method reference can easily be done using a higher-order function provided by J8+.

```java
List<String> nonEmptyStrings =
    list.stream()
      .filter(not(String::isEmpty))
      .collect(toList())

```
It filters out all empty String values and takes only non-empty ones.

# More About J8+
Please check out [the rest of docs](functions/funs) (work in progress)
