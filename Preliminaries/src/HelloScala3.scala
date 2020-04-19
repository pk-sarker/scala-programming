object HelloScala3 extends App {
  if (args.size == 0)
    println("Hi, Scala")
  else
    println("Hi, " + args(0))
}