# Control Structure
The control structures in Scala start off similar to Java and then diverge in some wonderful ways.\
For instance, Scala’s `if/then/else` structure is similar to Java, but can also be used to return a value. As a result, though Java has a special
syntax for a ternary operator, in Scala you just use a normal if statement to achieve the
ternary effect:

```scala
val x = if (s) y else z

if (a == b) {
    doX()
} else if (a == c) {
    doY()
} else {
    doZ()
}
```

The `try/catch/finally` structure is similar to Java, though Scala uses pattern matching
in the catch clause. This differs from Java, but because it’s consistent with other uses of
pattern matching in Scala.

## for and foreach loop
A Scala `for` loop can be used to iterate over the elements in a collection.
```scala
scala> val ar = Array("Kingfisher", "Parrot", "Magpie")
ar: Array[String] = Array(Kingfisher, Parrot, Magpie)

scala> for (element <- ar) println(element)
Kingfisher
Parrot
Magpie
```

When your algorithm requires multiple lines, use the same for loop syntax, and perform
your work in a block:
```scala
scala> for (element <- ar) {
     | val up = element.toUpperCase
     | println(up)
     | }
KINGFISHER
PARROT
MAGPIE
```

**Returning values from a for loop**\
In cases where we want to build a new collection from the input collection, use the `for/yield` combination.
```scala
scala> for(e <- ar) println(e)
Kingfisher
Parrot
Magpie

scala> val newAr = for (element <- ar) yield element.toUpperCase 
newAr: Array[String] = Array(KINGFISHER, PARROT, MAGPIE)

scala> def toUp(s: String): String = {
     |     s.toUpperCase
     | }
toUp: (s: String)String

scala> val newAr = for (element <- ar) yield toUp(element)
newAr: Array[String] = Array(KINGFISHER, PARROT, MAGPIE)

```

**For loop counters**\
To access counter inside a for loop, we can use one of the following approaches.
First, you can access array elements with a counter like this:
```scala
scala> for (i <- 0 until ar.length) {
     | println(s"$i - ${ar(i)}")
     | }
0 - Kingfisher
1 - Parrot
2 - Magpie
```

Scala collections also offer a `zipWithIndex` method that you can use to create a loop
counter:
```scala
scala> for ((e, index) <- ar.zipWithIndex) {
     | println(s"$index - ${ar(index)}")
     | }
0 - Kingfisher
1 - Parrot
2 - Magpie
```

**Generators and guards**
A Range can be use to execute a loop
```scala
scala> 0 to 5
res15: scala.collection.immutable.Range.Inclusive = Range 0 to 5

scala> for (i <- 0 to 5) println(i)
0
1
2
3
4
5
```
Use condition on loop
```scala
scala> for (i <- 0 to 10 if i%2 == 0) println(i)
0
2
4
6
8
10
```

**Looping over a Map**\
When iterating over keys and values in a Map, this to be the most concise and
readable for loop:
```scala
scala> val employee = Map("name"->"John", "duration"->5)
employee: scala.collection.immutable.Map[String,Any] = Map(name -> John, duration -> 5)

scala> for((k, v) <- employee) println(s"Key: $k Value: $v")
Key: name Value: John
Key: duration Value: 5
```
## While loop

## Exceptional Handling

