package lectures.basics.CBNvsCBV

object CBNvsCBV extends App {

  // Difference between Call by Name & Call by Value

  def calledByValue(x: Long): Unit = {
    println(s"by value: $x")
    println(s"by value: $x")
  }

  def calledByName(x: => Long): Unit = {
    println(s"by name: $x")
    println(s"by name: $x")
  }

  // => Tells the compiler that the parameter will be called by name

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  /* We would expect to see the same 2 time values, printed twice, however this is not the case

  calledByValue satisfies the above, calledByName returns two different times

  Why?

  In the example above, System.nanotime() is literally passed into the function at each instance of x occurring

  Hence why we get different nanotimes back

  This is called Calling by Name rather than Calling by Value */

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

  // Works without a stack overflow crash on an infinitely recursive function
  printFirst(34, infinite())

  /* Why?

  Because y is called by name rather than it's value, & y is not present in the function printFirst, it is never actually evaluated by the compiler*/

}
