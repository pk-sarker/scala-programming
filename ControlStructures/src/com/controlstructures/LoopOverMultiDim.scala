//package com.controlstructures

object LoopOverMultiDim extends App {
  for(i <- 1 to 2; j <- 1 to 2) println(s"($i, $j)")

  for(i <- 1 to 2; j <- 1 to 2; k<- 1 to 2) println(s"($i, $j, $k)")

}
