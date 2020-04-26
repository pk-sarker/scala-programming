# Control Structure
The control structures in Scala start off similar to Java and then diverge in some wonderful ways.\
For instance, Scala’s `if/then/else` structure is similar to Java, but can also be used to return a value. As a result, though Java has a special
syntax for a ternary operator, in Scala you just use a normal if statement to achieve the
ternary effect:

```scala
val x = if (s) y else z
```

The `try/catch/finally` structure is similar to Java, though Scala uses pattern matching
in the catch clause. This differs from Java, but because it’s consistent with other uses of
pattern matching in Scala.

