package com.controlstructures

object PatternInMatchExpressions extends App {
  case class Person(firstName: String, lastName: String)
  case class Cat(name: String)

  // trigger the constant patterns
  println(matchPattern(1))
  println(matchPattern(true))
  println(matchPattern(false))
  println(matchPattern("hi"))
  println(matchPattern(Nil))

  // trigger the sequence patterns
  println(matchPattern(List(9,1,2)))
  println(matchPattern(List(5,7,0,1)))
  println(matchPattern(List(5,2,3)))
  println(matchPattern(Vector(1,2,3)))

  // trigger the tuple patterns
  println(matchPattern((3,6))) // two element tuple
  println(matchPattern((1,4,7))) // three element tuple

  // trigger the constructor patterns
  println(matchPattern(Person("Melissa", "John")))
  println(matchPattern(Cat("Joyee")))

  // trigger the typed patterns
  println(matchPattern("Hello, world"))
  println(matchPattern(42))
  println(matchPattern(42F))
  println(matchPattern(Array(1,2,3)))
  println(matchPattern(Array("coffee", "apple pie")))
  println(matchPattern(Cat("Joyee")))
  println(matchPattern(List("apple", "banana")))
  println(matchPattern(Map(1->"Al", 2->"John")))

  // trigger the wildcard pattern
  println(matchPattern("33d"))

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
}
