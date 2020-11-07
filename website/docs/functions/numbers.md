---
id: numbers
title: Numbers
sidebar_label: Numbers (WIP)
---

## `isOdd`
```java
import j8plus.ArrayFuns;

Numbers.isOdd(0); // false
Numbers.isOdd(1); // true
Numbers.isOdd(2); // false
Numbers.isOdd(3); // true
```

## `ODD_INTEGER`
```java
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

import j8plus.ArrayFuns;

final List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5)
  .stream()
  .filter(ArrayFuns.ODD_INTEGER)
  .collect(toList());
// [1, 3, 5]
```


## `isEven`
```java
import j8plus.ArrayFuns;

Numbers.isEven(0); // true
Numbers.isEven(1); // false
Numbers.isEven(2); // true
Numbers.isEven(3); // false
```

## `EVEN_INTEGER`
```java
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

import j8plus.ArrayFuns;

final List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5)
  .stream()
  .filter(ArrayFuns.EVEN_INTEGER)
  .collect(toList());
// [0, 2, 4]
```
