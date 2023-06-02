package lectures.basics.arguments

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def trFactorial(n: Int, acc: Int): Int = {
    if (n <= 1) acc
    else trFactorial(n = n - 1, acc = n * acc)
  }

  println(trFactorial(n = 10, acc = 1))

  /*
  In this example, we always have to call the recursive function with an accumulator of 1

  Can we avoid this? Can we set it ourselves?

  Yes we can with default args.
  */

  @tailrec
  def defaultFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else defaultFactorial(n = n - 1, acc = n * acc)
  }

  println(defaultFactorial(n = 10))

  // Note - this doesn't stop us from overriding the default argument of 1 if we want to, but we now have its default set

  def savePicture(format: String, width: Int, height: Int): Unit = {
    println("This is now saved!")
  }

  // Assume our savePicture function is called mostly with JPEG format

  def defaultSavePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = {
    println("This is now saved!")
  }
  // defaultSavePicture(800, 600) // This now doesn't work - why? The compiler thinks that the input of 800 corresponds to the format parameter!

  // Leading arguments of functions as default parameters can cause problems when we want to call said function with it's default value

  // How can we get around this? We can explicitely name parameters when we pass them into the function.

  defaultSavePicture(width = 1000)
}
