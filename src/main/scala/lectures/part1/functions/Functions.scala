package lectures.part1.functions

object Functions extends App {

  def function(a: String, b: Int): String = {
    // concatenate our inputs
    a + "" + b
  }

  // We can assign our function expression to a value & print it
  val functionCall = function(a="hello", b=3)
  println(functionCall) // returns "hello3"

  // we can define functions without any parameters should we wish
  def parameterLessFunction(): Int = {
    42
  }

  println(parameterLessFunction()) // returns 42

  def aRepeatedFunction(stringInput: String, n: Int): String = {
    if (n == 1) stringInput
    else stringInput + aRepeatedFunction(stringInput = stringInput, n = n - 1)
    // this is now a recursive function - the function calls itself
    // this is extremely important to avoid dealing with for & while loops in functional programming
  }

  val hello3Times = aRepeatedFunction(stringInput = "hello", n = 3) // prints hellohellohello (string + string + string)
  val helloOnce = aRepeatedFunction(stringInput = "hello", n = 1) // prints just hello (string)
  println(hello3Times)
  println(helloOnce)

  // WHEN YOU NEED LOOPS IN YOUR PROGRAM, USE RECURSIVE FUNCTIONS

  def aFunctionWithSideEffects(inputString: String): Unit = {
    // Unit functions are good use cases for interacting with DB's, reading from storage, logging & printing to the console
    println(inputString)
  }

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

      aSmallerFunction(n, n-1)
  }

  /* Exercises:

  1). A greeting function for kids, takes a name & age, it says, "Hi, my name is <name> & I am <age> years old.

  2). A factorial function, i.e. 1 * 2 * 3 * 4 * ... * n

  3). A fibonacci function, i.e. f(1) = 1, f(2) = 1, f(n) = f(n - 1) f(n - 2)

  4). A function that tests whether a number is prime. */

}
