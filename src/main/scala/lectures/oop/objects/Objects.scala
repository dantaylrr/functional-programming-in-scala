package lectures.oop.objects

// Objects in Scala are dedicated concepts - which is why we haven't referred to class instantiation as "objects"

object Objects extends App {

  /*

  Class level functionality - functionality that does not depend on an instant of a class

  Everything we've done so far has been defined inside a class instantiation

  SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY - i.e. Scala does not have the java-ism of "static" or the Python-ism of class attributes

  Scala does have something even better however... here is how we can implement the same functionality in Scala

  */

  // THIS IS CALLED A "COMPANION OBJECT"
  object Person {
    val n_eyes = 2

    def canFly: Boolean = false

    // This is called a factory method - it is used to build new Persons
    def from(mother: Person, father: Person): Person = {
      new Person(name = "Bobby")
    }

    def apply(mother: Person, father: Person): Person = {
      new Person(name = "dan")
    }
  }

  class Person(val name: String) {

  }

  println(Person.n_eyes)
  println(Person.canFly)

  /*

  To use class level functionality in Scala, we use objects & companion objects to classes

  Scala objects are "Singletons" - single instances

  */

  // Notice we do not need to pass in the name parameter, here we are referring to the object instance of Person, not the class instance
  val mary = Person // Instance of the Person singleton type - notice omition of "new" key word, we are referring to the object not the class
  val john = Person // Another instance of the Person singleton
  println(mary == john) // Should return true

  /*

  It is common practice to use both Classes & Objects (companion objects) in same file, scope, applications

  They must be named the same. i.e. class Person, object Person

  This separates instance level functionality from static/class level functionality

  All data is there-by accessed via. some form of instantiation, either a class instantiation or a singleton instantiation

  Making Scala truly object-oriented

  */

  val person1 = new Person("Mary") // Here we are using the "new" key word, referring to the class
  val person2 = new Person("John")
  println(person1 == person2) // Will return false, these are different class with different signatures

  // We usually have factory methods inside companion objects

  val bobby = Person.from(person1, person2)

  // However, these methods are usually / typically called "apply()" - this is where the "apply" functionality is extremely strong

  // We can now call this apply function as the factory function on the Person class
  val dan = Person(person1, person2)
  println(dan.name)

  /*

  Scala Applications:

  A Scala application is only a Scala object with a very particular method.

  def main(args: Array[String]): Unit

  We haven't seen or used this anywhere - why not???

  "extends App"!

  We can also put all of our code inside a defined main() application, remove "extends App" & it will also be runnable!

  See the alternative Scala object for this lecture to see implementation of this.

  */
}
