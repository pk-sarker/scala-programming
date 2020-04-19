object StringToInt {

  def toInt(s: String):Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }

  def main(args: Array[String]) = {
    println(toInt("10"));
    println(toInt("b7"));
  }
}
