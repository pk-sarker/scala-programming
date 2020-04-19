# Scala Programming
This is the code and note repository for learning Scala Programming.

## Prerequisites & Installation
* Install `Java` 8 - used JDK `1.8.0_221`
* Install `SBT` - used `1.3.3`
* Install IDE of your choice which supports Scala (IDEA Community Edition with Scala plugin is preferred)- Used `2.11.12` and `2.13.1`

## Install references:
* https://www.scala-lang.org/download/2.12.6.html
* [Windows] https://www.journaldev.com/7456/download-install-scala-linux-unix-windows

## About Scala
### Object Oriented Programming:
Object-oriented programming (OOP) is a programming paradigm based on the concept of "objects", which can contain data, in the form of fields (often known as attributes or properties), and code, in the form of procedures (often known as methods).

### Functional Programming: 
Functional programming is a programming paradigm - a style of building the structure and elements of a computer program that treats computation as the evaluation of mathematical functions and avoids changing states and mutable data. 

### Pure function:
A pure function is a function that has the following properties:

  - Its output or return value is the same for same set of inputs/arguments.
  - Its evaluation has no side effects like: no mutation of local static variables, non-local variables, mutable reference arguments or I/O streams
### Immutable
In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created.

### Scala
Scala is a modern multi-paradigm programming language designed to express common programming patterns in a concise, elegant, and type-safe way. It is a general-purpose programming language providing support for functional programming and a strong static type system.

Scala source code is intended to be compiled to Java bytecode, so that the resulting executable code runs on a Java virtual machine. Scala provides language interoperability with Java, so that libraries written in either language may be referenced directly in Scala or Java code. Like Java, Scala is object-oriented, and uses a curly-brace syntax reminiscent of the `C` programming language.

Scala is a pure object-oriented language in the sense that every value is an object. Types and behaviors of objects are described by classes and traits. Classes can be extended by subclassing, and by using a flexible mixin-based composition mechanism as a clean replacement for multiple inheritance.

Scala is also a functional language in the sense that every function is a value. Scala provides a lightweight syntax for defining anonymous functions, it supports higher-order functions, it allows functions to be nested, and it supports currying.

Scala is statically typed. Scala’s expressive type system enforces, at compile-time, that abstractions are used in a safe and coherent manner. Scala has a built-in type inference mechanism which allows the programmer to omit certain type annotations. Type inference means the user is not required to annotate code with redundant type information.


### Functional Programming: 
Functional programming is a programming paradigm - a style of building the structure and elements of a computer program that treats computation as the evaluation of mathematical functions and avoids changing states and mutable data. 

### Pure function:
A pure function is a function that has the following properties:

  - Its output or return value is the same for same set of inputs/arguments.
  - Its evaluation has no side effects like: no mutation of local static variables, non-local variables, mutable reference arguments or I/O streams
### Immutable
In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created.

### Scala
Scala is a modern multi-paradigm programming language designed to express common programming patterns in a concise, elegant, and type-safe way. It is a general-purpose programming language providing support for functional programming and a strong static type system.

Scala source code is intended to be compiled to Java bytecode, so that the resulting executable code runs on a Java virtual machine. Scala provides language interoperability with Java, so that libraries written in either language may be referenced directly in Scala or Java code. Like Java, Scala is object-oriented, and uses a curly-brace syntax reminiscent of the `C` programming language.

Scala is a pure object-oriented language in the sense that every value is an object. Types and behaviors of objects are described by classes and traits. Classes can be extended by subclassing, and by using a flexible mixin-based composition mechanism as a clean replacement for multiple inheritance.

Scala is also a functional language in the sense that every function is a value. Scala provides a lightweight syntax for defining anonymous functions, it supports higher-order functions, it allows functions to be nested, and it supports currying.

Scala is statically typed. Scala’s expressive type system enforces, at compile-time, that abstractions are used in a safe and coherent manner. Scala has a built-in type inference mechanism which allows the programmer to omit certain type annotations. Type inference means the user is not required to annotate code with redundant type information.

#### Scala Environment
There are few thing in scala environment.
* _Scala Compiler_ - its called `scalac`. Scala compiler compiles Scala source files with .scala extension and creates a byte code files(with extension `.class`)
* _Scala runtime libraries_ -  Libraries required to run scala code (byte code) in Java Virtual Machine.
* _Scala Virtual Machine_ - Scala Virtual Machine is actually Java Virtual Machine with additional libraries for Scala (Scala runtime libraries) that provides various concepts, functionality and frameworks that Scala uses. The combination of JVM and the Scala libraries can be referred to as Scala runtime and can be used as interpreter mode (Scala REPL) or batch mode.
 
#### Scala REPL
Scala REPL (Read-Evaluate-Print-Loop) is a commandline interpreter to that you may use for quick test Scala code. To start Scala REPL write `scala` in terminal/command prompt. You should see like below
```scala
scala-programming pijus$ scala
Welcome to Scala 2.13.0 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_221).
Type in expressions for evaluation. Or try :help.

scala> 
scala> 1 + 2
res0: Int = 3
```
If you don't assign any variable the interpreter will automatically create variables starting with `res`. There will be sequential numbers after the variable prefix, like `res0`, `res1`, `res2` etc. 

To exit Scala REPL press `Ctrl-D` or write `:quit`
```scala
scala>:quit
```
 
#### Compile and Run Scala
```scala
// HelloScala.scala
object HelloScala {
  def main(args: Array[String]) = {
    println("Hello Scala");
  }
}
// Compile - creates byte codes (.class)
$ scalac HelloScala.scala

// Run - Run class file HelloScala.class
$ scala HelloScala
> Hello Scala
```
As Scala Virtual Machine is a combination of JVM and Scala runtime libraries, you can run java byte codes in Scala runtime environment.

```Java
// HelloJava.java
public class HelloJava {
    public static void main(String[] args){
        System.out.println("Hello Java");
    }
}

// Compile - creates byte codes (.class) 
$ javac HelloJava.java

// Run - Run class file HelloJava.class
$ scala HelloJava
> Hello Java
```

We can check the codes by disassembling both `HelloScala.class` and `HelloJava.class`.
```shell script
$ javap HelloJava.class
Compiled from "HelloJava.java"
public class HelloJava {
  public HelloJava();
  public static void main(java.lang.String[]);
}

$ javap HelloScala.class
Compiled from "HelloScala.scala"
public final class HelloScala {
  public static void main(java.lang.String[]);
}
```
As that output shows, the `javap` command reads that `.class` file just as if it was created from Java source code. Scala code runs on the JVM and can use existing Java libraries.

In `HelloScala.scala` example `main` methods is used, but Scala provides a way to write applications more conveniently. Instead of including `main` method use `App` trait. `App` trait has its own `main` method. `object`  can extend `App` trait.

```scala
object HelloScala2 extends App {
    println("Scala 123 abcd")
}

$ scalac HelloScala2.scala
$ scala HelloScala2
> Scala 123 abcd
```

We can access command-line arguments using `args` array. `args.size` or `args.length` can be used to check the number of elements in `args`.
```scala
object HelloScala3 extends App {
    if (args.size == 0)
        println("Hi, Scala")
    else
        println("Hi, " + args(0))
}

$ scalac HelloScala3.scala
$ scala HelloScala3
> Hi, Scala

$ scala HelloScala3 Pijus
> Hi, Pijus
```


### Variables
In java each variable declaration is preceded by its type. Like
```java
class Test {
    String str = new String();
    int year = 2020;
    Integer day = 13;
}

Test testObject = new Test();
```
but in Scala there are two type of variables:
* `val` creates an *immutable* variable, like final in Java.
* `var` creates an *mutable* variable.
We can reassign/change values in variable defined by `var` keyword as long as the data type is same as initial one. We can reassign/change values in variable defined by `var` keyword as long as the data type is same as initial one. 
Data type can't be change once the data type is assigned to the variable. 
```scala
var str = "Hello Scala" // Mutable
val year = 2020; // Immutable 
val day = 13; // Immutable 

// Reassign 
day = 14;
<console>:12: error: reassignment to val
       day = 14

str = "Hello ABC";
```
Scala compiler determines the data type of variable is compile time. Scala compiler is usually smart enough to infer the variable’s data type from the code on the right side of the `=` sign. We can also explicitly define the data type of a variable as well.
```scala
scala> var abc: String = "abc"
abc: String = abc

scala> var abc1 = "abc"
abc1: String = abc // infer data type 
```
As the variable data type inferred in compile time, the variables need to be initialized to be defined except `traits` and `abstract classes`. \
And defining data type is optional in variable declaration.

```scala
val obj = new Chocolate("Dark")              // preferred
val obj: Chocolate = new Chocolate("Dark")   // unnecessarily verbose
```


## Data Types
In Scala all standard numeric data types are objects, not primitive data types. \
Example of basic numeric data types
```scala
val b: Byte = 1
val x: Int = 1
val l: Long = 1
val s: Short = 1
val d: Double = 2.0
val f: Float = 3.0
```
If type is not explicitly defined `val x = 1` will default to an `Int`. \
So if you want one of the other data types — `Byte`, `Long`, or `Short` — you need to explicitly declare those types, as shown.\

Numbers with a decimal (like 2.0) will default to a `Double`, so if you want a Float you need to declare a Float, as shown in the last example.\
Because `Int` and `Double` are the default numeric types, you typically create them without explicitly declaring the data type:
```scala
val i = 123   // defaults to Int
val x = 1.0   // defaults to Double
```


Data types and their ranges:

| Data Type | Possible Values |
| --- | --- |
| Boolean | true or false |
| Byte	| 8-bit signed two’s complement integer (-2^7 to 2^7-1, inclusive)
  -128 to 127 |
| Short	| 16-bit signed two’s complement integer (-2^15 to 2^15-1, inclusive)
  -32,768 to 32,767 |
| Int | 32-bit two’s complement integer (-2^31 to 2^31-1, inclusive)
  -2,147,483,648 to 2,147,483,647 |
| Long | 64-bit two’s complement integer (-2^63 to 2^63-1, inclusive)
  (-2^63 to 2^63-1, inclusive) |
| Float | 32-bit IEEE 754 single-precision float
  1.40129846432481707e-45 to 3.40282346638528860e+38 |
| Double | 64-bit IEEE 754 double-precision float
  4.94065645841246544e-324d to 1.79769313486231570e+308d |
| Char | 16-bit unsigned Unicode character (0 to 2^16-1, inclusive)
  0 to 65,535 |
| String | a sequence of Char |

To check exact value of the data ranges
```shell script
scala> Byte.MinValue
res6: Byte = -128

scala> Byte.MaxValue
res7: Byte = 127

scala> Short.MinValue
res8: Short = -32768

scala> Short.MaxValue
res9: Short = 32767

scala> Int.MinValue
res10: Int = -2147483648

scala> Int.MaxValue
res11: Int = 2147483647

scala> Long.MinValue
res12: Long = -9223372036854775808

scala> Long.MaxValue
res13: Long = 9223372036854775807

scala> Float.MinValue
res14: Float = -3.4028235E38

scala> Float.MaxValue
res15: Float = 3.4028235E38

scala> Double.MinValue
res16: Double = -1.7976931348623157E308

scala> Double.MaxValue
res17: Double = 1.7976931348623157E308
```

For large numbers Scala also includes the types `BigInt` and `BigDecimal`.
```scala
var b = BigInt(1234567890)
var b = BigDecimal(123456.789)
```
A great thing about `BigInt` and `BigDecimal` is that they support all the operators you’re used to using with numeric types:
```shell script
scala> var b = BigInt(1234567890)
b: scala.math.BigInt = 1234567890

scala> b + b
res0: scala.math.BigInt = 2469135780

scala> b * b
res1: scala.math.BigInt = 1524157875019052100

scala> b += 1

scala> println(b)
1234567891
```

**Parsing numbers from string**
Use the `to*` methods that are available on a String (courtesy of the `StringLike` trait):
```shell script
scala> "10".toInt
res21: Int = 10

scala> "10".toByte
res22: Byte = 10

scala> "10".toLong
res23: Long = 10

scala> "10".toShort
res24: Short = 10

scala> "10".toDouble
res25: Double = 10.0
 
scala> "10.123456".toDouble
res27: Double = 10.123456

scala> "10".toFloat
res26: Float = 10.0
```


### Reference
[1] [Scala Documentation](https://docs.scala-lang.org)\
[2] Programming in Scala: A Comprehensive Step-by-Step Guide, (3rd ed.) [Martin Odersky, Lex Spoon and Bill Venners, 2016]\
[3] A Beginner's Guide to Scala, Object Orientation and Functional Programming (2nd ed.) [John Hunt 2018-03-03]\
[4] Scala Cookbook; Recipes for Object-Oriented and Functional Programming [Alvin Alexander, 2013-08-23]\
[5] [Scala programming language - Wiki](https://en.wikipedia.org/wiki/Scala_(programming_language))\
