package lectures.oop.sugar

object MethodNotations extends App {

  // Now we will define the class inside the object it self as opposed to outside of it - more on this later
  class Person(val name: String, favouriteMovie: String) {

    def likes(movie: String): Boolean = {
      if (favouriteMovie == movie) true
      else false
    }

    def hangOutWith(person: Person): String = {
      s"${this.name} is hanging out with ${person.name}."
    }

    def +(person: Person): String = {
      s"${this.name} is hanging out with ${person.name}"
    }

    def unary_! : String = {
      s"$name, what?!"
    }

    def apply(): String = {
      s"Hello, my name is $name, & I like $favouriteMovie."
    }
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception")) // Works as expected
  println(mary likes "Inception") // This works & is exactly the same function call as the above

  // This is know as infix or operator notation - it only works with objects that have 1 parameter

  // "Operators" in Scala

  val tom = new Person("Tom", "FightClub")
  println(tom hangOutWith mary)

  // We can even define our methods as operators, i.e. a method named "+" - & then use infix notation to call it...
  // i.e. tom + mary == tom hangOutWith mary == tom.hangOutWith(mary)

  // ALL OPERATORS IN SCALA ARE METHODS. i.e:

  println(1 + 2)
  println(1.+(2))

  // THIS IS CALLED SYNTACTIC SUGAR NOTATION
  // Akka actors have ! ?

  // Prefix notation:
  val x = -1
  val y = 1.unary_- // The same as the expression above

  // unary_prefix only works with the following operators: - + ~ !
  println(!mary)
  println(mary.unary_!)

  // Special method name apply()
  println(mary.apply())
  println(mary())

  // Whenever the compiler finds an object being called like a function - the apply method (has to be defined), will automatically be called

  /*

  Exercises:

  1). Overload the + operator in the Person class.

  The + operator must return a nickname for a given person.

  2). Add an age to the Person class.

  Add a unary_+ operator which increments the value of the age as a new Person.

  3). Add a "learns" method in the Person class.

  Takes a string input & outputs a string of <name> learns <language>.

  Add a "learnsScala" method that takes no parameters but calls the "learns" method with "scala" as an input.

  Use this in postfix notation (deprecated in Scala 3).

  4). Overload the apply method.

  The apply method will take an Int & will output "<name> watched <favouriteMovie> <Int> times."

  */

}
