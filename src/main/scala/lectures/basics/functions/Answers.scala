package lectures.basics.functions

object Answers extends App {

  // 1).

  // we can return either a string or a unit here, I will return a string as we want to avoid side-effects
  def greeting(name: String, age: Int): String = {
    s"Hi my name is $name & I am $age years old"
  }

  val greeting1 = greeting(name = "Daniel", age = 26)
  println(greeting1)
  println(greeting(name = "Daniel", age = 26)) // we can also avoid setting a global val & call the function directly if we want

  // 2).

  def factorial(n: Int): Int = {
    // our base class is n = 0, when we want the recursive function to end - we do not want to times by 0, we times by 1
    if (n == 0) 1
    else n * factorial(n = n - 1)
  }

  val fiveFactorial = factorial(n = 5)
  println(fiveFactorial) // gives 120

  def fibonacci(n: Int): Int = {
    // 0 & 1 are our base cases, after that we can call a recursive function until we end up with our base cases
    if (n == 0 | n == 1) 1
    else fibonacci(n = n - 1) + fibonacci(n = n - 2)
  }

  val fib6 = fibonacci(n = 6)
  /* We can visualise this:

     n does not equal 0 or 1, thus our first call gives us f(5) + f(4)
     n does not equal 0 or 1 for either of f(5) or f(4), f(5) = f(4) + f(3)... f(4) = f(3) + f(2)
     now we have f(4) + f(3) + f(3) + f(2)
     ton the next call, when we split each of these out, we get
     f(3) + f(2) + f(2) + f(1) + f(2) + f(1) + f(1) + f(0)
     we now have 2 cases that meet our base case, n = 1 & n = 0, thus, the above equates to
     f(3) + f(2) + f(2) + f(1) + f(2) + f(1) + 1 + 1
     if we keep going, we end up with
     f(2) + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
     1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 = 13, which is our final answer*/

  println(fib6)

  def isItPrime(n: Int): Boolean = {

    // define an auxillary function for the remainder
    def isPrimeUntil(a: Int): Boolean =
      // base case, if a <= 1 we can return true, it is prime
      if (a <= 1) true
      // else, compute n mod a, if the remainder does not equal zero, then recursively call isPrimeUntil
      else n % a != 0 && isPrimeUntil(a = a - 1)

    // kick off the recursive call with out input divided by 2, for odd numbers, default integer division behaviour returns an integer
    isPrimeUntil(a = n / 2)
  }

  val prime7 = isItPrime(n = 7)
  println(prime7) // return true

}
