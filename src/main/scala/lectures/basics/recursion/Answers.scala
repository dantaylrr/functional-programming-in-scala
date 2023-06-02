package lectures.basics.recursion

import scala.annotation.tailrec

object Answers extends App {

  // 1).

  @tailrec
  def concatenate(input: String, n: Int, accumulator: String): String = {
    if (n == 1) accumulator
    else concatenate(input = input, n = n - 1, accumulator = accumulator + input)
  }

  println(concatenate(input = "Hello", n = 10, accumulator = ""))

  @tailrec
  def isItPrime(x: Int, n: Int = 1): Boolean = {
    if (n > x / 2) true
    else if (x % n == 0 & n != 1) false
    else isItPrime(x = x, n = n + 1)
  }

  println(isItPrime(x = 2003))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibTailRec(i: Int, valuePrior: Int = 0, valueCurrent: Int = 1): Int = {
      if (i <= 2) valuePrior + valueCurrent
      else fibTailRec(i = i - 1, valuePrior = valueCurrent, valueCurrent = valuePrior + valueCurrent)
    }
    fibTailRec(i = n)
  }

  println(fibonacci(n = 7))

}
