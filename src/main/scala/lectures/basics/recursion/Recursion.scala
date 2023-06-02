package lectures.basics.recursion

import scala.annotation.tailrec

object Recursion extends App {
  // Using the factorial function we previously defined - we can return a result back to the program using a code block
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n = n - 1)
      println("Computed factorial of " + n)

      result
    }
  }

  // each call of a recursive function uses something called a stack frame on the JVM
  println(factorial(n = 10))

  // the issue with this approach is that it keeps all recursive calls on it's internal stack in memory - which is an issue
  // if we want to then use this method for a large number, our program will either fail or crash
  // this returns a stack overflow error

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      // base case
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // THIS IS CALLED TAIL RECURSION
    }
    factHelper(n, 1)
  }

    /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 2 * 3 * ... * 10 * 1)
    = accumulator (has now gone onto the if branch as x <= 1 is satisfied, returning our accumulator which is actually now just our factorial computation)
    = 1 * 2 * 3 * 4 * ... * 10 */

    println(anotherFactorial(n = 5000)) // this will now work without a stack overflow error

    // factHelper doesn't actually need to be an auxillary function

    // why doesn't this crash the JVM?

    /*
    else factHelper(x - 1, x * accumulator) <- does not need a new stack frame in order to compute the additional recursive computation, it can use the same one
    if we compare this to our prior function
    else n * factorial(n - 1) <- we can see that a new stack frame is required for our computation as we need to times factorial(n - 1) by n, resulting in the overflow */

    // the key ingredient to tail recursion is to use the recursive call as the last expression of the function

    //if you aren't sure if your function is recursive, you can add @tailrec before the function to tell the compiler your function is supposed to be tail recursive
    // if it isn't, the compiler will complain

    // when you need loops, aim to use tail recursion

    /*
    Exercises:

    1). Concatenate a string n times.
    
    2). Produce an isPrime function that is tail recursive.
    
    3). Produce a Fibonacci function that is tail recursive. // Hint: generally you need as many accumulators as you do recursive calls
    */
}
