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
### Loop with multiple counter
Sometime we need to iterate over non-linear lists/array, like multi-dimensional array. We can use for loop to do this
```scala
scala> for(i <- 1 to 2; j <- 1 to 2) println(s"($i, $j)")
(1, 1)
(1, 2)
(2, 1)
(2, 2)

scala> for(i <- 1 to 2; j <- 1 to 2; k<- 1 to 2) println(s"($i, $j, $k)")
(1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 2, 2)
(2, 1, 1)
(2, 1, 2)
(2, 2, 1)
(2, 2, 2)
```
     
## While loop

```scala
  var i = 0;
  while (i<6) {
    println(i)
    i += 1
  }
```

## Loop break and continue
Sometime we need to use a break or continue construct, but Scala doesn’t have break or continue keywords.\
Scala doesn't have break or continue statements, but we can use `scala.util.control.Breaks` 

```scala
object BreakAndContinue extends App {
  breakable {
    for (i <- 1 to 10) {
      println(i)
      if (i%2 ==0 && i > 6) break // break out of the for loop
    }
  }

  val searchMe = "pijus piper picked a peck of pickled peppers"
  var numPs = 0
  for (i <- 0 until searchMe.length) {
    breakable {
      if (searchMe.charAt(i) != 'p') {
        break // break out of the 'breakable', continue the outside loop
      } else {
        numPs += 1
      }
    }
  }
  println("Found " + numPs + " p's in the string.")
}
```

## Match Expression Like a switch Statement

In Scala `match` expression like a Java switch statement
```scala
scala> var i = 3
i: Int = 3

scala>   i match {
     |     case 1 => println("Monday")
     |     case 2 => println("Tuesday")
     |     case 3 => println("Wednesday")
     |     case 4 => println("Thursday")
     |     case 5 => println("Friday")
     |     case 6 => println("Saturday")
     |     case 7 => println("Sunday")
     |     case whoa => println("Unexpected case: " + whoa.toString)
     |   }
Wednesday


scala> val pet = "boy"
pet: String = boy

scala>   pet match {
     |     case "girl" => println("It's a girl")
     |     case "boy" => println("It's a boy")
     |   }
It's a boy
```

**@switch annotation**
It is recommended to used @switch annotation for matching which provides a warning at compile time if the switch can’t be compiled to a `tableswitch` or `lookupswitch` \
Compiling your match expression to a `tableswitch` or `lookupswitch` is better for performance,
because it results in a branch table rather than a decision tree. When a value
is given to the expression, it can jump directly to the result rather than working through
the decision tree.
```scala
object SwitchAnnotation extends App {

    val i = 3
    val x = (i: @switch) match {
      case 1 => println("Monday")
      case 2 => println("Tuesday")
      case 3 => println("Wednesday")
      case 4 => println("Thursday")
      case 5 => println("Friday")
      case 6 => println("Saturday")
      case 7 => println("Sunday")
      // catch the default with a variable so you can print it
      case _ => println("Others")
    }
}

scala> scalac SwitchAnnotation.scala
scala> scala SwitchAnnotation
Wednesday
```

**Matching Multiple Conditions**
There are cases when same business logic applies for different conditions, In Java we do like this:
```java
public class SwitchJava {
    public static void main(String[] args) {
        int i = 3;
        switch (i){
            case 1:
            case 2:
            case 3:
                System.out.println(">> One or Two or Three");
                break;
            case 4:
                System.out.println(">> Four");
                break;
        }
    }
}
```
In Scala: 
```scala
object SwitchMulti extends App {
  val i = 5
  i match {
    case 1 | 3 | 5 | 7 | 9 => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
    case _ => println("doing nothing")
  }
}
```

Sometimes we need to match one or more patterns in a match expression, and the pattern may be
a constant pattern, variable pattern, constructor pattern, sequence pattern, tuple pattern,
or type pattern. We can define a case statement for each pattern you want to match.

```scala

def matchPattern(x: Any): String = x match {
    // constant patterns
    case 1 => "Int: One"
    case "hi" => "String: Hi"
    case true => "Boolean: true"
    case false => "Boolean: false"
    case Nil => "Nil: empty"

    // sequence patterns
    case List(9, _, _) => "List(9, _, _): a three-element list with 9 as the first element"
    case List(5, _*) => "List(5, _*): a list beginning with 5, having any number of elements"
    case Vector(1, _*) => "Vector(1, _*): a vector starting with 1, having any number of elements"

    // tuples
    case (x, y) => s"(x, y): got $x and $y"
    case (x, y, z) => s"(x, y, z): got $x, $y, and $z"

    // constructor patterns
    case Person(first, "John") => s"found an John, first name = $first"
    case Cat("Joyee") => "found a cat named Joyee"

    // typed patterns
    case s: String => s"you gave me this string: $s"
    case i: Int => s"thanks for the int: $i"
    case f: Float => s"thanks for the float: $f"
    case a: Array[Int] => s"an array of int: ${a.mkString(",")}"
    case as: Array[String] => s"an array of strings: ${as.mkString(",")}"
    case d: Cat => s"cat: ${d.name}"
    case list: List[_] => s"thanks for the List: $list"
    case m: Map[_, _] => m.toString

    // the default wildcard pattern
    case _ => "Unknown"
}

case class Person(firstName: String, lastName: String)
case class Cat(name: String)
```

**Condition in Case Statements**
To add qualifying logic to a case statement in a match expression, such as
allowing a range of numbers, or matching a pattern, but only if that pattern matches
some additional criteria.

```scala
scala> val i = 14
i: Int = 14

scala> i match {
    case a if 0 to 9 contains a => println("0-9 range: " + a)
    case b if 10 to 19 contains b => println("10-19 range: " + b)
    case c if 20 to 29 contains c => println("20-29 range: " + c)
    case _ => println("Hmmm...")
}

10-19 range: 14
```
### Using a Match Expression Instead of `isInstanceOf`
There are different ways to check object type. We can use `isInstanceOf` method:
```scala
if (xyz.isInstanceOf[ClassName]) {
  // matched
}

scala> case class Person(firstName: String, lastName: String)
defined class Person

scala> val obj1 = Person("John", "Doe")
obj1: Person = Person(John,Doe)

scala> if (obj1.isInstanceOf[Person]) { 
     | println("Object is a instance of Person class")
     | }
Object is a instance of Person class
```

We can also do the check with match expression: 
```scala
scala> def isPerson(x: Any): Boolean = x match {
     | case p: Person => true
     | }
isPerson: (x: Any)Boolean

scala> isPerson(obj1)
res7: Boolean = true
```
## Exceptional Handling
val i = 6
val evenOrOdd = i match {
    case 1 | 3 | 5 | 7 | 9 => "odd"
    case 2 | 4 | 6 | 8 | 10 => "even"
}