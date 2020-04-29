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
- [Splitting Strings](#splitting-strings)
- [Substituting Variables into Strings](#substituting-variables-into-strings)
- [Iterate over Characters in a String](#iterate-over-characters-in-a-string)
- [Pattern matching](#pattern-matching)
- [Replacing Patterns in Strings](#replacing-patterns-in-strings)
- [Extracting Parts of a String That Match Patterns](#extracting-parts-of-a-string-that-match-patterns)
- [Accessing a Character in a String](#accessing-a-character-in-a-string)
- [Add custom methods to the String](#add-custom-methods-to-the-string)

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

### Splitting Strings
String split is also another common operation in string manipulation related work. `split` method in Scala is available on String objects.\
It is actually the split method in Java. The split method returns an array of String elements, which you can then treat as a normal Scala Array
```scala
scala> "Scala Intro".split(" ")
res1: Array[String] = Array(Scala, Intro)

scala> "Scala Intro".split(" ").foreach(println)
Scala
Intro
```

### Substituting Variables into Strings
`String Interpolation` solved the problem. To use basic string interpolation in Scala, precede your string with the letter `s` and include your variables inside the string, with each variable name preceded by a `$` character.
```scala
scala> val name = "Jhon"
name: String = Jhon

scala> val org = "ABC Inc"
org: String = ABC Inc

scala> val moved = 10
moved: Int = 10

scala> s"Currently $name works at $org. He moved there $moved years ago."
res5: String = Currently Jhon works at ABC Inc. He moved there 10 years ago.
```

In addition to putting variables inside strings, you can include expressions inside a string
by placing the expression inside curly braces `{}`

```scala
scala> s"Joye works ${count + 3} hours on Friday and Saturday"
res6: String = Joye works 8 hours on Friday and Saturday

scala> case class Employee(name: String, vacation: Int)
defined class Employee

scala> val poul = Employee("Poul", 15)
poul: Employee = Employee(Poul,15)

scala> val Joice = Employee("Joice", 21)
Joice: Employee = Employee(Joice,21)

scala> s"""Name:         Vacation:
     | ${Joice.name}          ${Joice.vacation}
     | ${poul.name}           ${poul.vacation}"""
res8: String =
Name:         Vacation:
Joice          21
Poul           15
```

The `s` that’s placed before each string literal is actually a method. Though this seems
slightly less convenient than just putting variables inside of strings, there are at least two
benefits to this approach:
* Scala provides other off-the-shelf interpolation functions to give you more power.
* You can define your own string interpolation functions.

`f` is another string interpolation function (`printf` style formatting). *f string interpolator,* which lets you use `printf` style formatting specifiers inside strings
```scala
scala> val salary = 123456.78901
salary: Double = 123456.78901

scala> f"Joice's yearly salary is $salary%1.3f"
res16: String = Joice's yearly salary is 123456.789
```

We can also use the `format` method as well
```scala
scala> "%s's yearly vacation is %d days".format(Joice.name, Joice.vacation)
res17: String = Joice's yearly vacation is 21 days
```

| Format specifier | Description |
| --- | --- |
| %c | Character |
| %d | Decimal number (integer, base 10) |
| %e | Exponential floating-point number |
| %f | Floating-point number |
| %i | Integer (base 10) |
| %o | Octal number (base 8) |
| %s | A string of characters |
| %u | Unsigned decimal (integer) number |
| %x | Hexadecimal number (base 16) |
| %% | Print a “percent” character |
| \% | Print a “percent” character |

**The raw interpolator**\
In addition to the `s` and `f` string interpolators, Scala 2.10 includes another interpolator
named `raw`. The raw interpolator *"performs no escaping of literals within the string."*
The following example shows how `raw` compares to the `s` interpolator:
```scala
scala> s"First\nSecond"
res18: String =
First
Second

scala> raw"First\nSecond"
res20: String = First\nSecond
```

### Iterate over Characters in a String
We can use `map` or `foreach` to iterate over the characters in a string:
```scala
// ASCII numbers of each character
scala> "Scala Intro".map(c => c.toInt)
res23: scala.collection.immutable.IndexedSeq[Int] = Vector(83, 99, 97, 108, 97, 32, 73, 110, 116, 114, 111)

scala> "Scala Intro".map(c => c.toLower)
res25: String = scala intro

scala> "Scala Intro".map(_.toUpper)
res28: String = SCALA INTRO

scala> "Scala Intro".filter(_ != 'a').map(_.toUpper)
res29: String = SCL INTRO

scala> for (c <- "Scala Intro") println(c)
S
c
a
l
a
 
I
n
t
r
o

scala> def cFunc(c: Char) = {
     |   c.toInt
     | }
cFunc: (c: Char)Int

scala> for (c <- "Scala Intro") yield cFunc(c)
res35: scala.collection.immutable.IndexedSeq[Int] = Vector(83, 99, 97, 108, 97, 32, 73, 110, 116, 114, 111)

scala> "ABCDE".map(c => (c.toByte+32+1).toChar)
res38: String = bcdef
```






### Pattern matching
To create a Regex object we need to invoke `.r` method in String. Then use that pattern with `findFirstIn` when you’re 
looking for one match, and `findAllIn` when looking for all matches.
```scala
scala> val pattern = "[0-9]+".r
pattern: scala.util.matching.Regex = [0-9]+

scala> pattern.findFirstIn("265 Main St, Suite# 1517")
res0: Option[String] = Some(265)

scala> pattern.findAllIn("265 Main St, Suite# 1517")
res1: scala.util.matching.Regex.MatchIterator = <iterator>

scala> pattern.findAllMatchIn("265 Main St, Suite# 1517")
res2: Iterator[scala.util.matching.Regex.Match] = <iterator>

scala> pattern.findAllMatchIn("265 Main St, Suite# 1517").foreach(println)
265
1517

scala> pattern.findAllIn("265 Main St, Suite# 1517").foreach(println)
265
1517
```

If `findAllIn` doesn’t find any results, an empty iterator is returned, so we can still write
 code just like that—we don’t need to check to see if the result is `null`. If we
rather have the results as an `Arra`y, add the `toArray` method after the `findAllIn` call:
```scala
scala> pattern.findAllMatchIn("265 Main St, Suite# 1517").toArray
res6: Array[scala.util.matching.Regex.Match] = Array(265, 1517)

scala> pattern.findAllMatchIn("Main St, Toronto, ON").toArray
res7: Array[scala.util.matching.Regex.Match] = Array()

scala> pattern.findAllMatchIn("265 Main St, Suite# 1517")
res8: Iterator[scala.util.matching.Regex.Match] = <iterator>

scala> pattern.findAllIn("Main St, Toronto, ON").foreach(println)

```

Another approach is to import the Regex class, create a Regex instance, and then use the instance
in the same way
```scala
scala> import scala.util.matching.Regex
import scala.util.matching.Regex

scala> val pattern = new Regex("[0-9]+")
pattern: scala.util.matching.Regex = [0-9]+

scala> pattern.findAllMatchIn("265 Main St, Suite# 1517").toArray
res10: Array[scala.util.matching.Regex.Match] = Array(265, 1517)

scala> val match1 = pattern.findFirstIn("Main St, Toronto, ON")
match1: Option[String] = None

scala> val match1 = pattern.findFirstIn("Main St, Toronto, ON").getOrElse("Street number not found")
match1: String = Street number not found

scala> val match1 = pattern.findFirstIn("265 Main St, Toronto, ON").getOrElse("Street number not found")
match1: String = 265
```

### Replacing Patterns in Strings
Most often we want to search for regular-expression patterns in a string, and replace them. Because a String is *immutable*, you can’t perform find-and-replace operations directly
 on it, but you can create a new String that contains the replaced contents. There are many ways to do that.
 
We can use `replaceAll` on a String,
```scala
scala> "265 Main St, Toronto, ON".replaceAll("[0-9]", "__")
res12: String = ______ Main St, Toronto, ON
```

We can create a regular expression and then call `replaceAllIn` on that expression,again remembering to assign the result to a new string
```scala
scala> val regex = "[0-9]".r
regex: scala.util.matching.Regex = [0-9]

scala> regex.replaceAllIn("265 Main St, Toronto, ON 123", "__")
res13: String = ______ Main St, Toronto, ON ______
```

### Extracting Parts of a String That Match Patterns
Sometimes we want to extract one or more parts of a string that match the regular-expression
patterns you specify.\
To do that, first define the regular-expression patterns you want to extract, placing parentheses around them so you can extract them as “regular-expression groups.” First, define the desired pattern:
```scala
scala> val pattern = "([0-9]+) ([A-Za-z]+)".r
pattern: scala.util.matching.Regex = ([0-9]+) ([A-Za-z]+)
```

Next, extract the regex groups from the target string:
```scala
scala> val pattern(streetNumber, streetName) = "265 MainSt"
streetNumber: String = 265
streetName: String = MainSt

scala> val MoviesNearCityStateRE = "movies near ([A-Za-z]+), ([A-Za-z]{2})".r
MoviesNearCityStateRE: scala.util.matching.Regex = Movies near ([A-Za-z]+), ([A-Za-z]{2})

scala> val MoviesNearCityStateRE(city, state) = "Movies near Oakville, ON"
city: String = Oakville
state: String = ON
```

### Accessing a Character in a String
we can use the Java `charAt` method to get a character at a specific position in a string:
```scala
scala> "Scala Intro".charAt(3)
res15: Char = l
```
However, the preferred approach is to use Scala’s `Array` notation:
```scala
scala> "Scala Intro"(3)
res16: Char = l

scala> "Scala Intro"(2)
res17: Char = a
```

### Add custom methods to the String

We can define an `implicit` class, and then define methods within that class to implement the behavior we want:
```scala
scala> implicit class StringToUp(s: String) {
     | def up = s.map(c => (c-32).toChar)
     | }
defined class StringToUp

scala> "abcd".up
res0: String = ABCD
```