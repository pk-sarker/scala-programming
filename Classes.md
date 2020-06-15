# Class
## Table of Contents
- [Class in Scala](#class-in-scala)
- [Class Constructor](#class-constructor)
- [Visibility of Constructor fields](#visibility-of-constructor-fields)
- [Case Class](#case-class)

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

## Auxiliary Constructor
Auxiliary constructions provided more flexibility to instantiate a class. 

Auxiliary constructors are methods in the class with the name `this`. We can
define multiple auxiliary constructors, but they must have different signatures (parameter
lists). Also, each constructor must call one of the previously defined constructors

```scala
class Meal(var time: String, var mainCourse: String, starter: String, dessert: String) {
  def this(time: String) {
    this(time, "Lunch", "Poutine","Ice Cream Sandwich")
  }
  def this(time: String, mainCourse: String) {
    this(time, mainCourse, "Poutine", "Ice Cream Sandwich")
  }
  def this(time: String, mainCourse: String, starter: String) {
    this(time, mainCourse, starter, "Ice Cream Sandwich")
  }

  // zero-arg auxiliary constructor
  def this() {
    this("Lunch",  "Smoked Meat", "Poutine", "Ice Cream Sandwich")
  }

  override def toString = s" $time =>  Main Course: $mainCourse, Starter: $starter, Dessert: $dessert "
}
```

```scala
scala> val lunch = new Meal()
lunch: Meal =  Lunch =>  Main Course: Smoked Meat, Starter: Poutine, Dessert: Ice Cream Sandwich

scala> lunch.toString()
res0: String = " Lunch =>  Main Course: Smoked Meat, Starter: Poutine, Dessert: Ice Cream Sandwich "

scala> val dinner = new Meal("Dinner")
dinner: Meal =  Dinner =>  Main Course: Smoked Meat, Starter: Poutine, Dessert: Ice Cream Sandwich

scala> val dinner2 = new Meal("Dinner", "Salad")
dinner2: Meal =  Dinner =>  Main Course: Salad, Starter: Poutine, Dessert: Ice Cream Sandwich

scala> val dinner3 = new Meal("Dinner", "Salad", "Chicken Wings")
dinner3: Meal =  Dinner =>  Main Course: Salad, Starter: Chicken Wings, Dessert: Ice Cream Sandwich

scala> val dinner3 = new Meal("Dinner", "Salad", "Chicken Wings", "Birthday Cake Soft Serve")
dinner4: Meal =  Dinner =>  Main Course: Salad, Starter: Chicken Wings, Dessert: Birthday Cake Soft Serve
```
## Visibility of Constructor fields
As we have seen in the last section, the visibility of the constructor fields in Scala is controlled by whether the fields are declared as `val`, `var`, without either `val` or `var`,
and whether `private` is also added to the fields.

* *If a field is declared as a `var`, Scala generates both getter and setter methods for that
field.*
* *If the field is a `val`, Scala generates only a getter method for it.*
* *If a field doesn’t have a `var` or `val` modifier, Scala gets conservative, and doesn’t
generate a getter or setter method for the field.*
* *Additionally, `var` and `val` fields can be modified with the `private` keyword, which
prevents getters and setters from being generated.*

**val field**\
If field type is defined as  val then its immutable, we can't change it. Like final in Java.
It should have an accessor method, and should not have a mutator method.

```scala
scala> class Person(val name: String)
defined class Person

scala> val p = new Person("Pijus")
p: Person = Person@293ba26c

scala> p.name
res7: String = Pijus

scala> p.name = "Kumar"
<console>:12: error: reassignment to val
       p.name = "Kumar"
              ^
```

**Fields without val or var**\
When neither val nor var are specified on constructor parameters, the visibility of the
field becomes very restricted, and Scala doesn’t generate accessor or mutator methods
```scala
scala> class Person(name: String)
defined class Person

scala> val p = new Person("Pijus Kumar")
p: Person = Person@7271320c

scala> p.name
<console>:13: error: value name is not a member of Person
       p.name
```

**Adding private to val or var**\
In addition to these three basic configurations, you can add the *private* keyword to a
val or var field. This keyword prevents *getter* and *setter* methods from being generated,
so the field can only be accessed from within members of the class:
```scala
scala> class Person(private var name: String) { def getName {println(name)} }
defined class Person

scala> val p = new Person("Pijus Kumar")
p: Person = Person@1d7914a5

scala> p.name
<console>:13: error: variable name in class Person cannot be accessed in Person
       p.name
```

## Case classes
Case classes are like regular classes with a few key differences which we will go over. Case classes are good for modeling immutable data.

A minimal case class requires the keywords `case class`, an identifier, and a parameter list (which may be empty):

```scala
scala> case class Perosn(name: String)
defined class Perosn

scala> val p = Perosn("Pijus Kumar")
p: Perosn = Perosn(Pijus Kumar)

scala> p.name
res10: String = Pijus Kumar
```

`new` keyword is not required to instantiate a case class. Case class has `apply` method which takes care of object construction.

When we create a case class with parameters, the parameters are public `val`
```scala
scala> case class Message(sender: String, recipient: String, body: String)
defined class Message

scala> val message1 = Message("pijus@email.com", "joe@email.com", "Test Message")
message1: Message = Message(pijus@email.com,joe@email.com,Test Message)

scala> println(message1.sender)
pijus@email.com
```

Instances of case classes are compared by structure and not by reference:
```scala
scala> val message1 = Message("pijus@email.com", "joe@email.com", "Test Message")
message1: Message = Message(pijus@email.com,joe@email.com,Test Message)

scala> val message2 = Message("pijus@email.com", "joe@email.com", "Test Message")
message2: Message = Message(pijus@email.com,joe@email.com,Test Message)

scala> val messagesAreTheSame = message1 == message2
messagesAreTheSame: Boolean = true
```

We can create a copy of the case class using `copy` method. It will be shallow copy.
```scala
scala> case class Pet(kind: String, name: String, adoptedOn: java.util.Date)
scala> val current_pet = Pet("Dog", "Tom", new java.util.Date())
current_pet: Pet = Pet(Dog,Tom,Mon Jun 15 13:19:27 BDT 2020)
scala> val last_pet = current_pet.copy(current_pet.kind, current_pet.name, new java.util.Date())
last_pet: Pet = Pet(Dog,Tom,Mon Jun 15 13:20:48 BDT 2020)
```