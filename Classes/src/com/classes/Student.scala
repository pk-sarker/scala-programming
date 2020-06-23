package com.classes

class Student private (val name: String, val rank: Int){

  def getName(): String = {
    name;
  }

  def getRank(): Int = {
    rank;
  }
}
//
// object Student {
//   val student = new Student("Pijus", 4)
//   def getInstance = student
// }

object Student {
  var student = new Student("", -1)
  def getInstance(name: String, rank: Int): Student = {
    if (student.getName() == "" && student.getRank() == -1) {
      student = new Student(name, rank)
    }
    return student
  }
  def getInstance(): Student = student
}
