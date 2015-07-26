# J8+ (J8Plus) - The Missing Functional Parts of Java 8

[![Build Status](https://travis-ci.org/Kevin-Lee/j8plus.svg?branch=master)](https://travis-ci.org/Kevin-Lee/j8plus)
[![Build Status](https://semaphoreci.com/api/v1/projects/dfc5d723-b5a8-43fa-a8fb-97d28d556de7/442898/badge.svg)](https://semaphoreci.com/kevin-lee/j8plus)

[![Coverage Status](https://coveralls.io/repos/Kevin-Lee/j8plus/badge.svg)](https://coveralls.io/r/Kevin-Lee/j8plus)
[ ![Download](https://api.bintray.com/packages/kevinlee/maven/j8plus/images/download.svg) ](https://bintray.com/kevinlee/maven/j8plus/_latestVersion)

[![views](https://sourcegraph.com/api/repos/github.com/Kevin-Lee/j8plus/.counters/views.svg)](https://sourcegraph.com/github.com/Kevin-Lee/j8plus)
[![views 24h](https://sourcegraph.com/api/repos/github.com/Kevin-Lee/j8plus/.counters/views-24h.svg)](https://sourcegraph.com/github.com/Kevin-Lee/j8plus)

## Why J8+?
Q: Java 8 comes with newly added functional features like Lamba Expressions and Method References so why do we need this library claiming that it contains some functional features that Java 8 does not have?

A: Java 8 has nicely implemented Lambda Expressions and Method References which meke all the existing libraries really useful and reusable, yet it still lacks some useful features that can make our lives a lot easiler. J8+ provides those missing functional features to make your life easier.

### Problem Examples

#### Recursion

Java 8's JVM does still not support [tail call optimization](http://en.wikipedia.org/wiki/Tail_call). So if you write a recursive method like the following one, you still get [StackOverflowError](http://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html).

```java
public static BigInteger factorial(final BigInteger acc, final BigInteger n) {
  if (n.equals(BigInteger.ONE)) {
    return acc;
  }
  return factorial(acc.multiply(n), n.subtract(BigInteger.ONE));
}
```

#### Opposite Operation

Another example is Stream in Java 8's collections. The Stream has a useful method called filter, yet, unlike Scala, it does not have a method like filterNot which does the opposite operation. Let's say you need to get all non empty String from the list. You do something like this using functional features in Java 8.

```java
List<String> nonEmptyStrings = list.stream()
                                   .filter(text -> !text.isEmpty())
                                   .collect(toList())

```

It can be even more concise with Method References, but unfortunate, you can't use it as Stream does not have the opposite operation method of filter.  So if it's taking all empty Strings you do like

```java
List<String> nonEmptyStrings = list.stream()
                                   .filter(String::isEmpty) // no filterNot method in Stream
                                   .collect(toList())

```
But you can't use a method reference to filter out the empty String.

### Solutions using J8+

#### Recursion
[StackOverflowError](http://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html) when using recursive method can be solved using Trampoline provided in J8+. It requires only a little changes in your code.

```java
public static TailCallable<BigInteger> factorial(final BigInteger acc, final BigInteger n) {
  if (n.equals(BigInteger.ONE)) {
    return done(acc);
  }
  return () -> factorial(acc.multiply(n), n.subtract(BigInteger.ONE));
}
```
Now, you don't need to worry about StackOverflowError.

#### Opposite Operation

Opposite operation when using method reference can easily be done using a higher-order function provided by J8+.

```java
List<String> nonEmptyStrings = list.stream()
                                   .filter(not(String::isEmpty))
                                   .collect(toList())

```
It filters out all empty String values and takes only non-empty ones.

# More About J8+


# Get J8+

### Maven
* Add maven repository

```xml
<repositories>
  ...

  <repository>
    <snapshots>
      <enabled>false</enabled>
    </snapshots>
    <id>bintray-kevinlee-maven</id>
    <name>bintray</name>
    <url>http://dl.bintray.com/kevinlee/maven</url>
  </repository>

  ...
</repositories>
```

* Add dependency

```xml
<dependencies>
  ...

  <dependency>
    <groupId>cc.kevinlee</groupId>
    <artifactId>j8plus</artifactId>
    <version>0.0.14</version>
  </dependency>

  ...
</dependencies>
```

### Gradle
* Add maven repository

  In `build.gradle`, add the following repository to `repositories`.

```gradle
maven {
  url  "http://dl.bintray.com/kevinlee/maven"
}
```
  e.g.)

```gradle
repositories {
  mavenCentral()
  maven {
    url  "http://dl.bintray.com/kevinlee/maven"
  }
}
```

* Add Dependency

```gradle
compile group: 'cc.kevinlee', name: 'j8plus', version: '0.0.14'
```
  OR

```gradle
compile "cc.kevinlee:testosterone:0.0.14"
```
