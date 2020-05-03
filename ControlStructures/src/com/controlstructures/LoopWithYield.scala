package com.controlstructures

object LoopWithYield extends App {
  for { i <- 1 to 10 } yield println(i)
}
