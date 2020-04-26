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
