package lectures.oop.caseclasses

object CaseClasses extends App {

  // Often for light-weight classes, we need to re-use lots of boiler plate implementations such as companion objects
  // Standard methods for equals, hashCode, prettyPrinting, toString etc.

  // Enter Case Classes!

  case class Person(name: String, age: Int) {

  }

  /*

  Case classes do a lot of things by default:

  1). Promote inputs/constructors to instance fields

  2). Sensible toString method

  3). equals & hashCOde are implemented by default - makes them extremely important for collections

  4). Case classes have copy methods

  5). Case classes have companion objects

  6). Case classes are serializable - really useful when using frameworks such as Akka

  7). Case classes have extractor patterns  = Case classes can be used in pattern matching, which is extremely powerful

  8). There is also a thing known as a case object

  */

  // 1).
  val jim = new Person("Jim", 34)
  println(jim.age)

  // 2).
  println(jim.toString) // Person(Jim, 34)

  // 3).
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // Now returns true

  // 4).
  val jim3 = jim.copy(age = 45) // New instance of Person with age overriden

  // 5).
  val thePerson = Person
  val mary = Person.apply("Mary", 23)

  // 8).
  case object UnitedKingdom {

    def name: String = "The United Kingdom & Great Britain & Northern Island."
  }

  /*

  Expand myList to use case classes & case objects whenever you see fit.

  */


}
