# String
Scala has `String` and `Char` data types. We can declare them in both implicit and explicit form. Use double quote for String and single quote for char.

```scala
scala> var str = "Book"
str: String = Book

scala> var chr = 'a'
chr: Char = a

// Explicit form
scala> var str: String = "Book"
str: String = Book

scala> var chr: Char = 'b'
chr: Char = b

```

The type `String` in Scala is Java `String`, It has all the methods of the underlying `java.lang.String`. 

```scala
scala> (new String("Hello ...")).getClass.getName
res5: String = java.lang.String

scala> "Hello ...".getClass.getName
res6: String = java.lang.String
```

Also the methods in `scala.collection.StringOps` class are added implicitly.
So we can do many other things with them, such as treating a String instance as a sequence of characters. As a result, you can iterate over every character in the string using the
foreach method:  
```scala
scala> "Hello A".foreach(println)
H
e
l
l
o
 
A
```

We can treat a String as a sequence of characters in a for loop
```scala
scala> for(c <- "Hello A") println(c)
H
e
l
l
o
 
A
```

We can also treat it as a sequence of bytes:
```scala
scala> "Hello B".getBytes.foreach(println)
72
101
108
108
111
32
66
```

We can use filters as well:
```scala
scala> "Hello ABC".filter(_ != 'B')
res12: String = Hello AC
```

The reality is that some of this functionality comes from `StringOps`, some comes from `StringLike`, some from `WrappedString`, and so on. If we check Scala source code, we’ll see that the it goes deep, but it begins
with the implicit conversion from String to `StringOps` in the `Predef` object.

_**Predef**\
The **Predef** object provides definitions that are accessible in all Scala compilation units without explicit qualification.\
Predef provides type aliases for types which are commonly used, such as the immutable collection types `scala.collection.immutable.Map`, `scala.collection.immutable.Set`, and the `scala.collection.immutable.List` constructors (`scala.collection.immutable.::` and `scala.collection.immutable.Nil`)._

## Table of Contents
- [Add Methods to Closed Classes](#add-methods-to-closed-classes)
- [String Equality](#string-equality)
- [Multiline Strings](#multiline-strings)

### Add Methods to Closed Classes 
Although String class in `Java` is *final* but with the help of *implicit conversions*.\
As one more example of how this pattern helps a Scala String have both string and
collection features, the following code uses the *dropRight*, *drop* and *take* methods that are available
on Scala sequences, along with the capitalize method from the `StringOps` class:

```scala
scala> "## scala intro".dropRight(5).drop(3).take(5).capitalize
res7: String = Scala
``` 
*drop* and *dropRight* are collection methods that drops (discards) the number of elements that are specified from
the beginning and from the end of the collection and keeps the remaining elements.\
*take* method retains the number of elements that are specified from the beginning of the collection it’s given, and discards the rest.

### String Equality
One of the most common operation with the string is to check if two strings are the same, whether they contain the same sequence of characters.
In Scala, comparison of two String instances are done by `==` operator. It doesn't throw `NullPointerException` if one or both string are `null`. 

```scala
scala> "Scala" == "Scala"
res12: Boolean = true

scala> "Scala" == null
res14: Boolean = false

scala> val s1 = "Scala"
s1: String = Scala

scala> val s2 = null
s2: Null = null

scala> s1 == s2
res15: Boolean = false
```

Strings needed to convert to uppercase or lowercase to compare strings in case-insensitive manner.
In this case it will throw `NullPointerException` if a string is `null`.
```scala
scala> val s1 = "Scala"
s1: String = Scala

scala> val s2 = "scala"
s2: String = scala

scala> s1 == s2
res17: Boolean = false

scala> s1.toUpperCase == s2.toUpperCase
res18: Boolean = true

scala> s1.toLowerCase == s2.toLowerCase
res19: Boolean = true

scala> val s2 = null
s2: Null = null

scala> s1.toLowerCase == s2.toLowerCase
<console>:14: error: value toLowerCase is not a member of Null
       s1.toLowerCase == s2.toLowerCase
```

We can also use `equals` and `equalsIgnoreCase` from `Java` String class to compare strings.

```scala
scala> s1.equals
equals   equalsIgnoreCase

scala> s1.equals(s2)
res21: Boolean = false

scala> s1.equalsIgnoreCase(s2)
res22: Boolean = true

scala> val s2 = null
s2: Null = null

scala> s1.equalsIgnoreCase(s2)
res23: Boolean = false

scala> s1.equals(s2)
res24: Boolean = false
```

In Scala, the `==` method defined in the `AnyRef` class first checks for `null` values, and then
calls the equals method on the first object (i.e., this) to see if the two objects are equal.
As a result, you don’t have to check for null values when comparing strings.

### Multiline Strings
In Scala multiline strings are created by surrounding texts with three double quotes:
```scala
scala> val s1 = """Name: ABCD
     | Location: Toronto
     | Age: 30"""
s1: String =
Name: ABCD
Location: Toronto
Age: 30

```

The results in a true multiline string, with a hidden `\n` character after the word "ABCD" and "Toronto" in the first two line. To convert this multiline string into one continuous line you can add a `replaceAll` method at the end, replacing all newline characters with blank spaces:
```scala
scala> val s1 = """Name: ABCD
     | Location: Toronto
     | Age: 30""".stripMargin.replaceAll("\n", " ")
s1: String = Name: ABCD Location: Toronto Age: 30
```