---
id: funs
title: Funs (Functions)
sidebar_label: Funs (Functions)
---

**Have lots of fun with Funs!**

Funs class contains methods that can be used with other functions or Stream API (or other Monads).

To use it, just do static import of `j8plus.Funs`.

```java
import static j8plus.Funs.*;
```


## `isNull()`
`isNull()` method returns a `Predicate` which checks if the given parameter is `null`.

```java
System.out.println(isNull().test(null));    // true
System.out.println(isNull().test(1));       // false
System.out.println(isNull().test("abc"));   // false
```

* It can be useful when using Stream.

```java
final List<String> listOfNull =
    Arrays.asList("a", "b", null, "c", null, null, "d")
        .stream()
        .filter(isNull())
        .collect(toList());

System.out.println("null found: " + listOfNull.size());
```

* Result:

```
null found:3
```

## `isNotNull()`
`isNotNull()` method returns a `Predicate` which checks if the given parameter is not `null`.

```java
System.out.println(isNotNull().test(null));   // false
System.out.println(isNotNull().test(1));      // true
System.out.println(isNotNull().test("abc"));  // true
```

* It can be useful when using Stream.

```java
final List<String> listOfNotNullString =
    Arrays.asList("a", "b", null, "c", null, null, "d")
        .stream()
        .filter(isNotNull())
        .collect(toList());

System.out.println("list of not null String values: " + listOfNotNullString);
```

* Result:

```
[a,b,c,d]
```

## `reversed()`
`reversed()` returns a `Comparator` which imposes the reversed order of the given Comparator.

### Examples

* With this simple JavaBean

```java
public static class Product {
  private Long id;
  private String name;
  private BigDecimal price;
  
  // getters and setters
  
  public Product price(final BigDecimal price) {
    setPrice(price);
    return this;
  }

  // remainder omitted...
}
```

```java
final List<Integer> numbers = Arrays.asList(4, 2, 5, 3, 1);

final Comparator<Integer> intCmp = Integer::compareTo;
final Comparator<Integer> reversedIntCmp = reversed(intCmp);

final List<Integer> numbersInAsc =
  numbers.stream()
    .sorted(intCmp)
    .collect(toList());
System.out.println("Number in ascending order: " + numbersInAsc);
// Number in ascending order: [1, 2, 3, 4, 5]

final List<Integer> numbersInDsc =
  numbers.stream()
    .sorted(reversedIntCmp)
    .collect(toList());
System.out.println("Number in descending order: " + numbersInDsc);
// Number in descending order: [5, 4, 3, 2, 1]
```

```java
final List<Product> products =
  Arrays.asList(product(1L, "A", new BigDecimal("30.00")),
                product(2L, "B", new BigDecimal("12.50")),
                product(3L, "C", new BigDecimal("5.45")));
final List<Product> productsSortedByPriceInAsc =
  products
    .stream()
    .sorted(comparing(Product::getPrice))
    .collect(toList());
System.out.println(
  "Products sorted by price in ascending order: \n" + productsSortedByPriceInAsc
);
// Products sorted by price in ascending order: 
// [
//   Product{id=3, name='C', price=5.45},
//   Product{id=2, name='B', price=12.50},
//   Product{id=1, name='A', price=30.00}
// ]

final List<Product> productsSortedByPriceInDsc =
  products
    .stream()
    .sorted(reversed(comparing(Product::getPrice)))
    .collect(toList());
System.out.println(
  "Products sorted by price in descending order: \n" + productsSortedByPriceInDsc
);
// Products sorted by price in descending order: 
// [
//   Product{id=1, name='A', price=30.00},
//   Product{id=2, name='B', price=12.50},
//   Product{id=3, name='C', price=5.45}
// ]
```

* `.sorted(BigDecimal::compareTo.reversed())` // This results in compile-time error but, the following one doesn't.

```java
final List<BigDecimal> bigDecimalsInDsc =
    Arrays.asList(new BigDecimal("3"), new BigDecimal("1"), new BigDecimal("2"))
        .stream()
        .sorted(reversed(BigDecimal::compareTo))
        .collect(toList());
System.out.println("bigDecimalsInDsc: " + bigDecimalsInDsc);
// bigDecimalsInDsc: [3, 2, 1]
```

## `toStringOf`
* `toStringOf` returns a `Function` which returns String. `toStringOf` takes a `Function` as a parameter then combines that with `String::valueOf`. So it will eventually work like

```java
// Parameter function: f
String.valueOf(f.apply(x))
```

* Exmaples

```java
System.out.println(
    products.stream()
            .map(Product::getPrice)
            .collect(joining(", ")) // compile-time error because BigDecimal is not String.
);
```

```java
System.out.println(
    products.stream()
            .map(toStringOf(Product::getPrice))
            .collect(joining(", "))
);
// 30.00, 12.50, 5.45
```

## `satisfying`

`satisfying` takes `BiPredicate<O, T>` and an additional parameter of type `T` then returns a `Predicate<O>`. This is meant to be used with method references to simplifying filtering. It would be much clear with some examples.

* Examples

Where there is a list of String and you want to filter in all String values start with a certain word. If you do it like this using lambda expressions.

```java
System.out.println(
  Arrays.asList("Hello world", "Hello Kevin", "Hi world", "Hey", "Hello")
        .stream()
        .filter(s -> s.startsWith("Hello"))
        .collect(toList())
);
// [Hello world, Hello Kevin, Hello]
```
Because `startWith()` method takes a parameter, you can't use method reference.
```java
.filter(String::startsWith("Hello")) // You cannot pass parameters to a method reference.
```

However, if you use `satisfying()` method, you can. Just like this.
```java
System.out.println(
  Arrays.asList("Hello world", "Hello Kevin", "Hi world", "Hey", "Hello")
        .stream()
        .filter(satisfying(String::startsWith, "Hello"))
        .collect(toList())
);
// [Hello world, Hello Kevin, Hello]
```

## `applying`

```java
final List<Product> products = Arrays.asList(
    product(1L, "A", $("30.00")),
    product(2L, "B", $("12.50")),
    product(3L, "C", $("5.45"))
  );

final BigDecimal specialPrice = new BigDecimal("10.00");
System.out.println(
    products
        .stream()
        .map(applying(Product::price, specialPrice))
        .collect(toList())
);
// [
//   Product{id=1, name='A', price=10.00},
//   Product{id=2, name='B', price=10.00},
//   Product{id=3, name='C', price=10.00}
// ]
```
