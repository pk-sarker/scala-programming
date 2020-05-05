# Class
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
### Primary Constructor
The primary constructor of a Scala class is a combination of:
* The constructor parameters
* Methods that are called in the body of the class
* Statements and expressions that are executed in the body of the class

Fields declared in the body of a Scala class are handled in a manner similar to Java; they
are assigned when the class is first instantiated

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