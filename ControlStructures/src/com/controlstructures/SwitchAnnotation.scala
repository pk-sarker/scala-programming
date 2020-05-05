package com.controlstructures
import scala.annotation.switch

object SwitchAnnotation extends App{

  val i = 3
  val x = (i: @switch) match {
    case 1 => println("Monday")
    case 2 => println("Tuesday")
    case 3 => println("Wednesday")
    case 4 => println("Thursday")
    case 5 => println("Friday")
    case 6 => println("Saturday")
    case 7 => println("Sunday")
    // catch the default with a variable so you can print it
    case _ => println("Others")
  }

  println(getClassAsString(i))

  def getClassAsString(x: Any): String = x match {
    case s: String => s + " is a String"
    case i: Int => "Int"
    case f: Float => "Float"
  }
}

