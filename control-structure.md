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

### How for loops are translated
Its good to understand how for loops are translated by Scala compiler
1. A simple for loop that iterates over a collection is translated to a `foreach` method
   call on the collection.
   ```scala
    object LoopA extends App {
      for (i <- 1 to 5) println(i)
    }
   ```
   
   ```scala
   $ scalac -Xprint:parse LoopA.scala
    [[syntax trees at end of parser]] // LoopA.scala
    package <empty> {
      object LoopA extends App {
        def <init>() = {
          super.<init>();
          ()
        };
        1.to(5).foreach(((i) => println(i)))
      }
    }
   ```
   
2. A for loop with a *guard* is translated to a sequence of a `withFilter`
   method call on the collection followed by a foreach call.
   ```scala
    object LoopWIthCondition extends App {
      for(i <- 1 to 12 if i%3 == 0) println(i)
    }
   ```
   ```scala
   $ scalac -Xprint:parse LoopWIthCondition.scala
   
   [[syntax trees at end of parser]] // LoopWIthCondition.scala
   package <empty> {
     object LoopWIthCondition extends App {
       def <init>() = {
         super.<init>();
         ()
       };
       1.to(12).withFilter(((i) => i.$percent(3).$eq$eq(0))).foreach(((i) => println(i)))
     }
   }
   ```
3. A for loop with a `yield` expression is translated to a `map` method call on the collection. 
    ```scala
    object LoopWithYield extends App {
      for { i <- 1 to 10 } yield println(i)
    }
    ```
    ```scala
    $ scalac -Xprint:parse LoopWithYield.scala
    
    [[syntax trees at end of parser]] // LoopWithYield.scala
    package com.controlstructures {
      object LoopWithYield extends App {
        def <init>() = {
          super.<init>();
          ()
        };
        1.to(10).map(((i) => println(i)))
      }
    }
    ```
 4. A for loop with a `yield` expression and a guard is translated to a `withFilter`
    method call on the collection, followed by a `map` method call.
    ```scala
    object LoopWithConditionAndYield extends App {
      for ( i <- 1 to 10 if i < 8) yield println(i)
    }
    ```
    ```scala
    $ scalac -Xprint:parse LoopWithConditionAndYield.scala
    
    [[syntax trees at end of                    parser]] // LoopWithConditionAndYield.scala
    package <empty> {
      object LoopWithConditionAndYield extends App {
        def <init>() = {
          super.<init>();
          ()
        };
        1.to(10).withFilter(((i) => i.$less(8))).map(((i) => println(i)))
      }
    }
    ```     
## While loop

## Exceptional Handling

