package lectures.functional.Functions

object Answers extends App {

  // 1).

  def concantenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concantenator("Hello", "World"))

  // 2). See MyList for full solution & implementation in that context with Generics - below are simple examples using the "Any" data type

  /*

  trait MyPredicate[-T] {
    def test(elem: T): Boolean
  }

  trait MyTransformation[-A, B] {
    def transformer(transform: A): B
  }

  */

  def myPredicate: Any => Boolean = new Function[Any, Boolean] {
    override def apply(v1: Any): Boolean = {
      if (v1 == 2) true
      else false
    }
  }

  println(myPredicate(2))
  println(myPredicate("Hello"))

  def myTransformation: Any => Any = new Function[Any, Any] {
    override def apply(v1: Any): Any = s"Here is the string: ${v1.toString}"
  }

  println(myTransformation(2))

  // 3).

  /*

  def func2: Int => Int = new Function[Int, Int] {
    override def apply(x: Int): Int = x + y
  }

  */

  // A higher order function - we receive a function "func2" as a result -> THIS IS CALLED A CURRIED FUNCTION
  def func1: Int => Function[Int, Int] = new Function[Int, Function[Int, Int]] {
    override def apply(y: Int): Function[Int, Int] = new Function[Int, Int] {
      override def apply(x: Int): Int = x + y
    }
  }

  val functionReturned = func1(5) // This returns a function - whilst also passing in y = 7 into func1
  val outputFromFunction = functionReturned(7) // Now passing x = 7 into out return function from the line above
  println(outputFromFunction) // Should give us 12
  // We can then shorthand the above in 1 call
  val result = func1(5)(7)
  println(result) // Gives us 12 again (Curried function)

}
