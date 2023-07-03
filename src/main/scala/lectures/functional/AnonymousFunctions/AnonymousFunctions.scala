package lectures.functional.AnonymousFunctions

object AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }

  // This is still long winded & is primarily OOP - let's work around this, the equivalent of the above is...

  val doubler2 = (x: Int) => x * 2 // This is equivalent to new Function1[Int, Int] { override def apply(v1: Int): Int = v1 * 2 }

  // The above is called a LAMBDA, or an anonymous function

  // Some syntax rules... the above is definitely better & more succinct than what we used initially... however, let's make it more readable

  val doubler3: Int => Int = x => x * 2 // Here, the type of x is inferred by Int => Int

  // What about multiple parameters?

  val adder: (Int, Int) => Int = (a, b) => a + b

  // What about no parameters?

  // We make sure to use empty parenthesis ()
  val justDoSomething: () => Int = () => 3

  println(justDoSomething) // Returns an instance of the lambda function
  println(justDoSomething()) // Returns 3

  // Why? Well justDoSomething is only referring to the anonymous Lambda function object, whereas justDoSomething() is calling the apply method directly

  // We must call Lambda's with parenthesis!

  // We can also write Lambda's with curly braces - THIS IS THE COMMON STYLE TRADITIONALLY

  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // "_ + 1" is equivalent to x => x + 1
  val niceIncrementerTwo: (Int, Int) => Int = _ + _ // "_ + _" is equivalent to (x, y) => x + y

  // This is useful for chaining higher-order function calls, but is extremely contextual!
  
  // FULLY "_" CAN ONLY BE USED FOR NONE-CURRIED FUNCTIONS! I.E. FUNCTIONS THAT DO NOT CALL ANOTHER FUNCTION DIRECTLY!

  /*

  Exercises:

  1). Go to MyList & replace all FunctionX calls with Lambda's

  2). Re-write the curried adder from the "Functions" exercises (exercise 3).) as Lambda functions

  */

}
