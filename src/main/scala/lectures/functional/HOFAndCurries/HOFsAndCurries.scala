package lectures.functional.HOFAndCurries

object HOFsAndCurries extends App {

  // What keeps us from defining a super function?
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null // Something like this...
  // This is called a higher-order function (HOF)... map, flatmap & filter are all HOFs

  // We want to define a function that applies a function n times over a given n

  // nTimes(f, n, x) where f is the function, n is the amount of times, x is the input value

  // = f(f(f(x))) == f(f, 2, f(x)) == f(f, 2, (f, 1, x))) == f(f(f(x))) ... we can start to derive a recursive pattern here...

  // f(f(...f(x))) == f(f, n - 1, f(x))) == f(f, n - 1, (f, n - 2, f(x)))) ... until n = 1

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))
  }

  val plusOne = (x:Int) => x + 1 // Our function
  println(nTimes(plusOne, 10, 1)) // Returns 11

  // There is a better way to implement this

  // nTimesBetter(f,n) = x => f(f(f...(x)))       -> We can return a lambda expression & apply it n times to x

  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne...(x))      -> x => plusOne(plusOne...(x)) == lambda function that can be continuously applied

  // val y = increment10(1)         -> Use increment10 as though it was a lambda function on value 1

  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if (n <= 0) (x: Int) => x // We return the lambda identity function
    else (x: Int) => nTimesBetter(f, n - 1)(f(x)) // Recursively call nTimesBetter as a lambda function
  }

  val plusTen = nTimesBetter(plusOne, 10) // Apply plusOne 10 times on a parameter x we pass in
  println(plusTen(1)) // Returns 11

  // Curried functions:
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // Returns a lambda function x = 3, y is undefined ==== y => 3 + y
  println(add3(10)) // Prints 13

  // Scala supports another type of curried function by supporting functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  // Chaining the use of curriedFormatter - we have to still specify the Double type input
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  // Pass in our double (Pi)
  println(standardFormat(Math.PI)) // 3.14
  println(preciseFormat(Math.PI)) // 3.14159265
}
