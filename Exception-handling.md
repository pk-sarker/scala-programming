# Exception Handling

Like Java, Scala has a try/catch/finally construct to let you catch and manage exceptions. The main difference is that for consistency, Scala uses the same syntax that `match` expressions use: `case` statements to match the different possible exceptions that can occur.

Here’s an example of Scala’s try/catch syntax. In this example, `openAndReadAFile` is a method that does what its name implies: it opens a file and reads the text in it, assigning the result to the variable named text:
```scala
var text = ""
try {
    text = openAndReadAFile(filename)
} catch {
    case e: FileNotFoundException => println("Couldn't find that file.")
    case e: IOException => println("Had an IOException trying to read that file")
    case _: Throwable => println("Got some other kind of Throwable exception")
}
```

Scala uses the `java.io.*` classes to work with files, so attempting to open and read a file can result in both a `FileNotFoundException` and an `IOException`. Those two exceptions are caught in the catch block of this example.

The Scala `try/catch` syntax also lets you use a `finally` clause, which is typically used when you need to close a resource.