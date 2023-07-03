package lectures.oop.exceptions

// 3).

class OverflowException extends Exception {
  println("Result exceeds Int maximum.")
}

class UnderflowException extends Exception {
  println("Result exceeds Int minimum.")
}

class MathCalculationException extends Exception {
  println("Cannot divide by zero!")
}

object PocketCalculator {

  def add(x: Int, y: Int): Int = {
    // if ((x + y) > Int.MaxValue) throw new OverflowException - this is the logical way to do this, unfortunately we need to implement a clever trick
    // When the MAX_VALUE is exceeded, the integer turns negative
    if (x > 0 && y > 0 && (x + y) < 0) throw new OverflowException
    else if (x < 0 && y < 0 && (x + y) > 0) throw new UnderflowException
    else x + y
  }

  def subtract(x: Int, y: Int): Int = {
    if (x > 0 && y < 0 && (x - y) < 0) throw new OverflowException
    else if (x < 0 && y > 0 && (x - y) > 0) throw new UnderflowException
    else x - y
  }

  def multiply(x: Int, y: Int): Int = {
    if (x > 0 && y > 0 && (x * y) < 0) throw new OverflowException
    else if (x < 0 && y < 0 && (x * y) < 0) throw new OverflowException
    else if (x > 0 && y < 0 && (x * y) > 0) throw new UnderflowException
    else if (x < 0 && y > 0 && (x * y) > 0) throw new UnderflowException
    else x * y
  }

  def divide(x: Int, y: Int): Int = {
    if (y == 0) throw new MathCalculationException
    else x / y
  }
}

object Testing extends App {

  // 1).
  // val OOMError = throw new OutOfMemoryError("I'm out of Memory!")

  // 2).
  // val SOError = throw new StackOverflowError("Stack Overflowed!")

  // 3).
  val calc = PocketCalculator

  // Overflow add catch
  try {
    calc.add(x = Int.MaxValue, y = 993949301)
  } catch {
    case e: OverflowException => println("Caught our Overflow exception!")
  }

  // Underflow subtract catch
  try {
    calc.subtract(x = Int.MinValue, y = 3473742)
  } catch {
    case e: UnderflowException => println("Caught our Underflow exception!")
  }

  // Divide by zero catcher
  try {
    calc.divide(x = 1, y = 0)
  } catch {
    case e: MathCalculationException => println("Caught our dividing by zero exception!")
  }

}
