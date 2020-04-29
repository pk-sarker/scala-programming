package com.controlstructures


object ForLoop extends App {

  val ar = Array("Kingfisher", "Parrot", "Magpie")
  for (element <- ar) println(element)

  println("\nLoop over array and convert to uppercase")
  for (element <- ar) {
    val up = element.toUpperCase
    println(up)
  }

  println("\nReturning values from a for loop")
  val newAr = for (element <- ar) yield element
  for (element <- newAr) println(element)

  def toUp(s: String): String = {
    s.toUpperCase
  }
  println("\n")
  val ar2 = for (element <- ar) yield toUp(element)
  for (element <- ar2) println(element)

  println("\nFor loop counters")
  for (i <- 0 until ar.length) {
    println(s"$i - ${ar(i)}")
  }

  println("\n")
  for ((e, index) <- ar.zipWithIndex) {
    println(s"$index - ${ar(index)}")
  }

  println("\nGenerators and guards")
  for (i <- 0 to 5) println(i)

  println("\n")
  for (i <- 0 to 10 if i%2 == 0) println(i)

  println("\nLooping over a Map")
  val employee = Map("name"->"John", "duration"->5)
  for((k, v) <- employee) println(s"Key: $k Value: $v")
}
