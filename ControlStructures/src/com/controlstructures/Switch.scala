package com.controlstructures

object Switch extends App {
  var i = 3
  i match {
    case 1 => println("Monday")
    case 2 => println("Tuesday")
    case 3 => println("Wednesday")
    case 4 => println("Thursday")
    case 5 => println("Friday")
    case 6 => println("Saturday")
    case 7 => println("Sunday")
    // catch the default with a variable so you can print it
    case whoa => println("Unexpected case: " + whoa.toString)
  }

  val pet = "boy"
  pet match {
    case "girl" => println("It's a girl")
    case "boy" => println("It's a boy")
  }
}
