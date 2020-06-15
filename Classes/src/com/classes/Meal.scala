package com.classes

class Meal(var time: String, var mainCourse: String, starter: String, dessert: String) {
  def this(time: String) {
    this(time, "Smoked Meat", "Poutine","Ice Cream Sandwich")
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
