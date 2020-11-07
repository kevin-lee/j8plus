---
id: array-funs
title: ArrayFuns
sidebar_label: ArrayFuns
---

## `isEmpty`
```java
import j8plus.ArrayFuns;

final String[] emptyArray = {};
final String[] nonEmptyArray = { "abc", "def" };

ArrayFuns.isEmpty(emptyArray); // true
ArrayFuns.isEmpty(nonEmptyArray); // false

```

## `isNotEmpty`
```java
import j8plus.ArrayFuns;

final String[] nonEmptyArray = { "abc", "def" };
final String[] emptyArray = {};

ArrayFuns.isNotEmpty(nonEmptyArray); // true
ArrayFuns.isNotEmpty(emptyArray); // false

```

