package lectures.functional.Functions

object WhatIsAFunction extends App {

  /*

  Use & work with functions as 1st class elements. What does this mean?

  In it's simplest terms, we want to interact with functions as we would plain values

  However, there's a problem with this... we come from an Object-Orientated world - the JVM is built for this

  */

  class Action {
    def exec(element: Int): String = ???
  }

  // Traditionally, we would have to instantiate a class & then call it's function - we would traditionally do this using abstract data types such as traits

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  // Doubler can run as a function using the apply method
  println(doubler(2))

  // Function types = Function1[A, B] ... Function22

  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4) // Should return the value 7

  // () -> Syntactic sugar notation
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  println(adder(3, 4)) // Should again print 7

  // Function types: Function[A, B, R] == (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS OR INSTANCES OF CLASSES DERIVING FROM FUNCTION1, FUNCTION2, ... FUNCTIONN, N <= 22

  /*

  Exercises:

  1). Define a function that takes 2 strings & concatenates them

  2). Transform the MyPredicate & MyTransformer into function types (from MyList - OOP answers)

  3). Define a function which takes an argument: Int & returns another function which takes an Int & returns an Int

    - Define the type of the function
    - How to implement the above?

  */

}
