package lectures.functional.Sequences

import scala.util.Random

object Sequences extends App {

  // Sequences = Seq

  val aSequence = Seq(1, 2, 3, 4)
  println(aSequence) // Produces a list
  println(aSequence.reverse)
  println(aSequence(2)) // The default apply method issues a 'get' for the value provided
  println(aSequence ++ Seq(5, 6)) // New sequence List(1, 2, 3, 4, 5, 6)
  println(aSequence.sorted)

  // Ranges are also sequences
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  println(aRange)

  // Lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList // or ::
  println(prepended)
  val appended = aList :+ 89 //
  println(appended)

  val apples5 = List.fill(5)("apple") // Fill is a curried function that creates a value n & creates a list of n times the value provided
  println(apples5)
  println(aList.mkString("-"))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3) // Allocates space for 3 elements without providing any values
  threeElements.foreach(println) // Shows that they have default values of 0 or False, for String types, the values are initialised with Null

  // Mutation
  numbers(2) = 0 // Sugar for numbers.update(2, 0) -> very similar to apply & is a special method in scala
  println(numbers.mkString(" "))

  // Arrays & Sequences
  val numbersSeq: Seq[Int] = numbers // This is called an implicit conversion
  println(numbersSeq) // Returns an "ArraySeq"

  // This is how arrays have access to sequence methods such as mkString etc...

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // Vectors vs Lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for { // Collection of numbers
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      // Operation - amount of time it takes for the collection to be updated at a random index
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  // Saves the reference to the tail -> updating an element in the middle takes a long time
  println(getWriteTime(numbersList))
  // Needs to traverse the entire tree - depth of the tree is smaller
  println(getWriteTime(numbersVector))
}
