package lectures.functional.AnonymousFunctions

object Answers extends App {

  // 2).

  def function = (x: Int) => (y: Int) => x + y

  // Function takes an input "x" & returns another function with input "y" which implements x + y
  // Recall we cannot use the "_" operator here as our function is curried

  val result = function(5)(10)
  println(result)
}
