# Class
## Table of Contents
- [Class in Scala](#class-in-scala)
- [Class Constructor](#class-constructor)

## Class in Scala
In support of object-oriented programming (OOP), Scala provides a class construct. The syntax is much more concise than languages like Java and C#, but it’s also still easy to use and read.

Although Scala and Java share many similarities, the declaration of classes, class constructors,
and the control of field visibility are some of the biggest differences between
the two languages. Whereas Java tends to be more verbose (yet obvious), Scala is more
concise, and the code you write ends up generating other code.\
Here is a example of basic class constructure with  two parameters, `firstName` and `lastName`:
```scala
class Person(var firstName: String, var lastName: String)
```
Defining parameters in a class constructor automatically creates fields in the class. Create an instance of `Person`:
```scala
scala> val p = new Person("Bill", "Panner")
p: Person = Person@771a7d53

scala> p.firstName
res0: String = Bill

scala> p.lastName
res1: String = Panner
```
In this example, because both fields are defined as `var` fields, they’re also mutable, meaning they can be changed. This is how you change them:
```scala
scala> p.firstName = "John"
p.firstName: String = John

scala> p.lastName = "Doe"
p.lastName: String = Doe
```
By defining the parameter type `val` we can make the fields read-only:
```scala
class Person(val firstName: String, val lastName: String)

scala> val p = new Person("Bill", "Panner")
p: Person = Person@796f632b

scala> p.firstName
res0: String = Bill

scala> p.firstName = "John"
<console>:12: error: reassignment to val
       p.firstName = "John"
```

Fields declared in the body of a Scala class are handled in a manner similar to Java; they
are assigned when the class is first instantiated.

## Class Constructor
The primary constructor of a Scala class is a combination of:
* The constructor parameters
* Methods that are called in the body of the class
* Statements and expressions that are executed in the body of the class

Fields declared in the body of a Scala class are handled in a manner similar to Java; they’re assigned when the class is first instantiated.

This Person class demonstrates several of the things you can do inside the body of a class:

```scala
class Person(var firstName: String, var lastName: String) {
  println("the constructor begins")
  // some class fields
  private val HOME = System.getProperty("user.home")
  var age = 0
  // some methods
  override def toString = s"$firstName $lastName is $age years old"
  def printHome { println(s"HOME = $HOME") }
  def printFullName { println(this) } // uses toString
  printHome
  printFullName
  println("still in the constructor")
}
```

Because the methods in the body of the class are part of the constructor, when an instance
of a `Person` class is created, you’ll see the output from the println statements at the
beginning and end of the class declaration, along with the call to the printHome and
printFullName methods near the bottom of the class:
```scala
scala> val p = new Person("Adam", "Meyer")
the constructor begins
HOME = /Users/Al
Adam Meyer is 0 years old
still in the constructor
```

Here the two constructor arguments `firstName` and `lastName` are
defined as `var` fields, which means that they’re variable, or mutable; they can be changed
after they’re initially set. Because the fields are mutable, Scala generates both accessor
and mutator methods for them.
```scala
scala> val p = new Person("Adam", "Meyer")
the constructor begins
HOME = /Users/pijussarker
Adam Meyer is 0 years old
still in the constructor
p: Person = Adam Meyer is 0 years old

scala> println(p.firstName)
Adam

scala> p.firstName = "Pijus"
p.firstName: String = Pijus

scala> println(p.firstName)
Pijus

scala> p.age = 30
p.age: Int = 30

scala> println(p.age)
30
```

The field `HOME` declared as *private* *val* which is similar to making it *private* and *final* in Java class.  
```scala
scala> p.HOME
<console>:13: error: value HOME in class Person cannot be accessed in Person
       p.HOME
         ^

scala> p.printHome
HOME = /Users/pijussarker
```
