# Class
## Table of Contents
- [Class in Scala](#class-in-scala)
- [Class Constructor](#class-constructor)
- [Visibility of Constructor fields](#visibility-of-constructor-fields)
- [Private Primary Constructor](#private-primary-constructor)
- [Default Values for Constructor Parameters](#default-values-for-constructor-parameters)
- [Overriding Default Accessors and Mutators](#overriding-default-accessors-and-mutators)
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

## Auxiliary constructors for case classes
A *case class* is a special type of class that generates a lot of boilerplate code for us.
Because of the way they work, adding what appears to be an auxiliary constructor to a
case class is different than adding an auxiliary constructor to a “regular” class. This is
because they’re not really constructors: they’re apply methods in the companion object
of the class.

To demonstrate this, assume that you start with this case class in a file named
*Person.scala*:
```scala
// initial case class
case class Person (var name: String, var age: Int)
```
This lets you create a new *Person* instance without using the `new` keyword, like this:
```scala
val p = Person("Pijus K. Sarker", 34)
```
This appears to be a different form of a constructor, but in fact, it’s a little syntactic sugar
a factory method, to be precise. When you write this line of code
```scala
val p = Person("Pijus K. Sarker", 34)
val p = Person.apply("Pijus K. Sarker", 34)
```
This is a call to an `apply` method in the companion object of the *Person* class. We don’t
see this, you just see the line that you wrote, but this is how the compiler translates your
code. As a result, if you want to add new “constructors” to your case class, you write
new apply methods. (To be clear, the word “constructor” is used loosely here.)

To add auxiliary constructors to let you create new *Person* instances (a) without specifying any parameters, and (b) by only specifying their name, 
the solution is to add apply methods to the companion object of the *Person* case class in the *Person.scala* file:
```scala
// the case class
case class Person (var name: String, var age: Int)

// the companion object
object Person {
  def apply() = new Person("<no name>", 0)
  def apply(name: String) = new Person(name, 0)
}
```

```scala
scala> val a = Person()
a: Person = Person(<no name>,0)

scala> val b = Person("Pijus K. Sarker")
b: Person = Person(Pijus K. Sarker,0)

scala> val c = Person("Pijus K. Sarker", 34)
c: Person = Person(Pijus K. Sarker,34)
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

## Private Primary Constructor
To make the primary constructor private, insert the `private` keyword in between the class name and any parameters the constructor accepts:
```scala
// a private no-args primary constructor
class Person private { 
.
.
}

// a private one-arg primary constructor
class Student private (name: String) {
.
.
}
```
We can't instantiate the classes in usual way using the new keyword because the constructor is private.
```scala
class Student private (var name: String, val rank: Int){
  def getName(): String = {
    name;
  }
  def getRank(): Int = {
    rank;
  }
  def setName(n: String) {
    name = n
  }
}

scala> var s = new Student("John", 55)
<console>:12: error: constructor Student in class Student cannot be accessed in object $iw
       var s = new Student("John", 55)
``` 
We can create the instance of the class from its companion object. This is a way to enforce the Singleton pattern in Scala is to make the primary constructor private, then put a `getInstance` method in the companion object of the class:

```scala
class Student private (var name: String, val rank: Int){
  def getName(): String = {
    name;
  }
  def getRank(): Int = {
    rank;
  }
  def setName(n: String) {
    name = n
  }
}

object Student {
  val student = new Student("Pijus", 4)
  def getInstance = student
}


scala> val s = Student.getInstance
s: Student = Student@601d6622

scala> s.getName()
res0: String = Pijus

scala> s.getRank()
res1: Int = 4

scala> val s1 = Student.getInstance
s1: Student = Student@601d6622

scala> s1.getName()
res2: String = Pijus

scala> s1.getRank()
res3: Int = 4

scala> s1.setName("Kumar")

scala> s1.getName()
res5: String = Kumar

scala> s.getName()
res6: String = Kumar
```

In the above example we have created a Singleton pattern where the objects gets refers to one instance of `Student` class.
In  `Student` class we can change the name because `name` is defined as `var` and and `rank` as `val` and we have a setter method `setName()`.

Consider a situation where you will be creating a instance of a class with fixed values and don't change anything afterwords. 
```scala
class Student private (val name: String, val rank: Int){

  def getName(): String = {
    name;
  }

  def getRank(): Int = {
    rank;
  }
}

object Student {
  var student = new Student("", -1) // default value
  def getInstance(name: String, rank: Int): Student = {
    if (student.getName() == ""  && student.getRank() == -1) {
      student = new Student(name, rank)
    }
    return student
  }

  def getInstance(): Student = student
}
```
By changing the type of name and rank to val make sure that the values are immutable, so you can't reassign them. 
In the companion object, we have defined student with default value until you set something. Once `def getInstance(name: String, rank: Int)` is called with values other than default values (empty string and -1) 
a new instance of Student class is instantiated and will be returned when `getInstance` method is called.

```scala
scala> var s1 = Student.getInstance("Jhon", 9)
s1: Student = Student@415a3f6a

scala> s1.getName()
res0: String = Jhon

scala> s1.getRank()
res1: Int = 9

scala> var s2 = Student.getInstance()
s2: Student = Student@415a3f6a

scala> s2.getName()
res2: String = Jhon

scala> s2.getRank()
res3: Int = 9

scala> var s3 = Student.getInstance("Josheph", 10)  // It will discard the inputs as the instance of the Student class is already created.
s3: Student = Student@415a3f6a

scala> s3.getName()
res4: String = Jhon

scala> s3.getRank()
res5: Int = 9
```

## Default Values for Constructor Parameters
We can provide the parameter a default value in the constructor declaration:

```scala
scala> class Connect (val host: String = "localhost", val port: Int = 8080)
defined class Connect

scala> val connect = new Connect()
connect: Connect = Connect@31f4fdc5

scala> connect.host
res6: String = localhost

scala> connect.port
res7: Int = 8080

scala> val connect1 = new Connect("127.0.0.1", 8090)
connect1: Connect = Connect@5ceb6796

scala> connect1.host
res8: String = 127.0.0.1

scala> connect1.port
res9: Int = 8090
```

## Overriding Default Accessors and Mutators
There are situations when we want to override default getter and setter methods generated by Scala. 
Here is an example with private constructor:
```scala
class Student(private var name: String) {
	def name = name
	def name_=(aName: String) { name = aName }
}

<pastie>:12: error: overloaded method name needs result type
	def name = name
                   ^
<pastie>:13: error: method name_= is defined twice;
  the conflicting variable name was defined at line 11:27
	def name_=(aName: String) { name = aName }
```
The problem is that both the constructor parameter and the getter method are named name, and Scala won’t allow that.

To solve this problem, change the name of the field you use in the class constructor so it won’t collide with the name of the getter method you want to use. A common approach is to add a leading underscore to the parameter name, so if you want to manually create a getter method called name, use the parameter name _name in the constructor, then declare your getter and setter methods according to the Scala conventions
```scala
class Student(private var _name: String) {
 def name = _name
}

scala> var s = new Student("Pijus")
s: Student = Student@6d6d480c

scala> s.name
res0: String = Pijus


// For Private constructor variables you can only access them from inside the calsss.
class Student3(private var name: String) {   
}
scala> var s3 = new Student3("Sarker")
s3: Student3 = Student3@35d88a54

scala> s3.name
<console>:13: error: variable name in class Student3 cannot be accessed in Student3
       s3.name

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