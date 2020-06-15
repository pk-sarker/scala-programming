package com.classes

// the case class
case class Person2 (var name: String, var age: Int)

// the companion object
object Person2 {
  def apply() = new Person2("<no name>", 0)
  def apply(name: String) = new Person2(name, 0)
}
