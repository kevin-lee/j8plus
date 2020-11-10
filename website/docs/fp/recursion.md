---
id: recursion
title: 'Recursion'
sidebar_label: 'Recursion'
---
## Recursion Causes Stack Overflow
JVMs of Java 8 and higher still don't support [tail call optimization](http://en.wikipedia.org/wiki/Tail_call). So if you write a recursive method like the following one, you still get [StackOverflowError](http://docs.oracle.com/javase/8/docs/api/java/lang/StackOverflowError.html).

```java
public class App {
  public static Long termial(final Long n, final Long acc) {
    if (n >= 1) {
      return termial(n - 1, acc + n);
    } else {
      return acc;
    }
  }

  public static void main(final String[] args) {
    System.out.println(termial(100000L, 0L));
  }
}
```
```
Exception in thread "main" java.lang.StackOverflowError
	at App.termial(App.java:4)
	at App.termial(App.java:4)
	at App.termial(App.java:4)
	at App.termial(App.java:4)
	at App.termial(App.java:4)
	at App.termial(App.java:4)
	at App.termial(App.java:4)
...
```

## No Stack Overflow with Trampoline
It can be solved using `TailCalls.trampoline()` with small changes in the original method.

Changes required:
1. In the original method, put the return type in the type parameter of `TailCallable`.
   
   e.g.) `Long` => `TailCallable<Long>`.
   
2. When it's the end case, return the value with `TailCalls.done()` method.

   e.g.) `return acc;` => `return TailCalls.done(acc);`

3. Use a lambda expression for supplier for the recursive method call.
   
   e.g.) `return termial(n - 1, acc + n);` => `return () -> termial(n - 1, acc + n);`

```java
import j8plus.recursion.TailCallable;
import static j8plus.recursion.TailCalls.*;

public class App {
  public static TailCallable<Long> termial(final Long n, final Long acc) {
    if (n >= 1) {
      return () -> termial(n - 1, acc + n);
    } else {
      return done(acc);
    }
  }

  public static void main(final String[] args) {
    System.out.println(trampoline(termial(100000L, 0L)));
    // 5000050000L
  }
}
```
