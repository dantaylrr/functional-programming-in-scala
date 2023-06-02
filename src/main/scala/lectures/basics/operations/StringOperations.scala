package lectures.basics.operations

object StringOperations extends App {

  val str: String = "Hello I am learning Scala"

  // Standard string operations

  println(str.charAt(2)) // Returns a character at an index - note: indexing starts at 0

  println(str.substring(7, 11)) // Substring from characters 7 inclusive to 11 non-inclusive

  println(str.split(" ").toList) // Returns an array, split by the regex specified

  println(str.startsWith("Hello")) // Returns a boolean

  println(str.replace(" ", "-")) // Replaces characters with the specified character

  println(str.toLowerCase()) // Returns a string all in lower case

  println(str.length) // Returns the length of a string

  // All of these are Java functions / utilities, Scala actually has it's own String utilities as well

  val aNumberString = "45"
  val aNumber = aNumberString.toInt // Easily change string typings
  println('a' +: aNumberString) // Prepend a character to a string
  println(aNumberString :+ 'a') // Appending a character to a string
  println(str.reverse) // Reverse a string
  println(str.take(2)) // Take two characters from a string

  // Useful Scala specific things:

  // S-Interpolator
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name & I am $age years old." // This definitely isn't a new concept
  val anotherGreeting = s"Hello, my name is $name & I am ${age + 1} years old."

  // F-Interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f per minute." // 2 Characters total, 2 decimals of precision - gives 1.20

  // Raw-Interpolator
  println(raw"This is a \n newline.") // Prints as is
  val escaped = "This is a \n newline."
  println(raw"$escaped") // The new line is printed here

  // The raw-interpolator does not apply to injected strings

}
