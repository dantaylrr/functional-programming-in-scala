package lectures.oop.exceptions

object Exceptions extends App {

  // Extremely similar concept to any other programming language

  /*

  val x: String = null
  println(x.length) // Will crash our program

  */

  // 1). We can throw exceptions if we want to
  // val aWeirdValue: String = throw new NullPointerException // This is very valid - however, it returns Nothing!
  // Note - Nothing is a valid substitute for any type!

  // Exceptions are actually instances of their own classes! (Hence the new keyword)

  // Throwable classes extend the "Throwable" class
  // Exception & Error are the major Throwable subtypes

  // 2). What about catching exceptions?

  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No Int for you!")
    else 42
  }

  try {
    getInt(true)
  } catch {
    case exceptions: RuntimeException => println("Caught a Runtime Exception!")
  } finally {
    // Finally clauses get executed no matter what
    println("Finished!")
  }

  // What is the value of try/catch/finally?

  val potentialFail = try {
    getInt(false)
  } catch {
    case exceptions: RuntimeException => println("Caught a Runtime Exception!")
  } finally {
    // Finally clauses get executed no matter what
    println("Finished!")
  } // Returns 42

  // It's an "AnyVal" type - that's because the value can change depending on the case!

  // "finally" is completely optional & does not influence the return type of this expression - use finally for side-effects, such as logging

  // 3). How do we define our own Exceptions?

  class MyException extends Exception {

    val exception = new MyException

    throw exception // Will crash the program
  }

  /*

  Exercises:

  1). Crash your program with an OutOfMemory error

  2). Crash your program with a StackOverflow error

  3). Define a class PocketCalculator with custom exceptions
    - add(x,y)
    - subtract(x,y)
    - multiply(x,y)
    - divide(x,y)

  * OverflowException if add(x,y) exceeds Int.MAX_VALUE
  * UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
  * MathCalculationException for division by zero

  */

}
