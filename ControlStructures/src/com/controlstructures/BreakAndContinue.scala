// package com.controlstructures
import util.control.Breaks._

object BreakAndContinue extends App {
  breakable {
    for (i <- 1 to 10) {
      println(i)
      if (i%2 ==0 && i > 6) break // break out of the for loop
    }
  }

  val searchMe = "pijus piper picked a peck of pickled peppers"
  var numPs = 0
  for (i <- 0 until searchMe.length) {
    breakable {
      if (searchMe.charAt(i) != 'p') {
        break // break out of the 'breakable', continue the outside loop
      } else {
        numPs += 1
      }
    }
  }
  println("Found " + numPs + " p's in the string.")
}
