package lectures.part1.basics

object ValuesVariablesTypes extends App {

  /* Values or "val" 's are immutable. They cannot be changed & should generally be thought
     of as constants to be used in future computations. */

  /* Types can be inferred by the compiler if the type isn't explicitely stated.*/
  val x: Int = 42
  println(x)

  /* Scala's native types: */
  val stri: String = "Hello World!"
  val bool: Boolean = true
  val char: Char = 'a'
  val int: Int = x
  val short: Short = 6464 // 2-byte integer type
  val long: Long = 923923984834L // requires an L at the end of the value (2.xx)
  val float: Float = 7.0 // requires an f at the end of the value (2.xx)
  val double: Double = 3.143

  /* Mutable variables "var" ' s can also be used in Scala (although generally discouraged).*/
  var aVariable: Int = 4
  aVariable = 5 // this is known as a side-effect
}
