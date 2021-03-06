package com.exceptionhandling

import java.io.{FileNotFoundException, IOException}

import scala.io.Source

object FileExceptions extends App {
  var text: List[String] = Nil
  try {
    val absolute_path_to_file = "/Users/pijussarker/myws/RND/scala/scala-programming/ControlStructures/src/com/exceptionhandling"
    text = openAndReadAFile(  absolute_path_to_file + "/test.log")
    for ((e, index) <- text.zipWithIndex) {
      println(index + ": " + e)
    }
  } catch {
    case e: FileNotFoundException => println("Couldn't find that file.")
    case e: IOException => println("Had an IOException trying to read that file")
  } finally {
    println("-- Complete --")
  }
  // This code block will throw exception as the file path is not correct
  try {
    text = openAndReadAFile(   "/wrong_path/test.log")
    for ((e, index) <- text.zipWithIndex) {
      println(index + ": " + e)
    }
  } catch {
    case e: FileNotFoundException => println("Couldn't find that file.")
    case e: IOException => println("Had an IOException trying to read that file")
  } finally {
    println("-- Complete --")
  }

  def openAndReadAFile(filepath: String): List[String] = {
    return Source.fromFile(filepath).getLines.toList
  }
}
