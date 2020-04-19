object Variables extends App {
  var str = "Hello Scala" // Mutable
  val year = 2020; // Immutable
  val month: String = "April";

  // Compiler exception
  // var month = "June"
  // <console>:12: error: reassignment to val
  //     month = "June"
  //           ^

  // Compiler exception
  // var day;

}
