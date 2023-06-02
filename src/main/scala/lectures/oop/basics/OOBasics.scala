package lectures.oop

object OOBasics extends App {

  val person = new Person("Daniel", 26) // Instanciating a class is done with the key word "new"

  /* If we wanted to print the person's age to the console, we would typically do it by calling the fields from the class as follows

  println(person.age)

  This isn't how to achieve this in Scala - age is a class parameter, but isn't a class member in this case

  "age" here is also known as a private val - it is only accessible from within the class itself*/

  val person2 = Person2("Daniel", 26)

  println(person2.age) // This now works

  println(person2.x)

  /* The greetAnotherPerson function has the potential to fail here with the same name inputs...

  What if we instantiate a Person2 class with the name Daniel, but call the greetAnotherPerson method with the name "Matthew"... i.e.

  Person2("Daniel", 26)

  greetAnotherPerson("Matthew") >> "Hey Daniel, from Daniel." - which isn't the desired effect.

  We can work around this by using the "this" keyword... "this.name" pulls the name field from the class instantiation

   >> The compiler is smart enough to know the name value of the instantiation, we do not necessarily have to set it as an field */

  println(person2.greetAnotherPerson("Matthew"))
}

// A class organises data & behaviour

class Person(name: String, age: Int) // A class with parameter inputs is called a CONSTRUCTOR

/* Class parameters are not fields

We can ensure that any parameter we pass into the class is auto-assigned as a field by using "val" in the parameter */

class Person2(name: String, val age: Int) {
  // The class body, defines the implementation of this class
  val x = 2

  println(1 + 3)

  def greetingInstantiator(name: String = name): Unit = {
    println(s"Greetings ${name}, thanks for instantiating this class!")
  }

  greetingInstantiator() // We can call the greeting function upon instantiation

  def greetAnotherPerson(name: String): Unit = {
    println(s"Hey ${this.name}, from ${name}.")
  }

  def greet(): Unit = {
    println(s"Hey! I am ${name}.") // Here, name is implied, it is the same as this.name... we do not pass a name parameter into the function
  }

  // Multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")

} // The value of the code block is ignored, all code is evaluated when a class is instantiated - hence why "4" is the 1st element printed when we run this app

/*

Exercises:

1). Implement a Novel & a Writer class.

Writer should have a first name, surname, a year of birth & a method called fullName which returns their full name.

Novel should have a name, a year of release, an author (which is of type writer)... methods of:
 - authorAge - age of author when the book was released
 - isWrittenBy(author)
 - copy which receives a new year of release & returns a new instance of Novel with the new year of release

2). Implement a Counter class.

Receives an Int value & has a method which returns the current count, as well as a method to increment & decrement the counter by 1, returning a new Counter.
Also need to overload the inc/dec methods to receive an amount, with the result being a new counter.

*/
