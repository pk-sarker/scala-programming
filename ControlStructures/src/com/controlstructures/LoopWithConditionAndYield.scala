package com.controlstructures

object LoopWithConditionAndYield extends App {
  for ( i <- 1 to 10 if i < 8) yield println(i)
}
