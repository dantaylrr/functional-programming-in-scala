package lectures.oop.anonymity

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // This is called an anonymous class - this happens when we implement a new class with an on-the-spot implementation
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("Nom...")
  }

  println(funnyAnimal.getClass)

  // This is the equivalent to the below:

  /*

  // The compiler actually does something like the below behind the scenes:
  class AnonymousClasses$$anon$1 extends Animal {
    override def eat: Unit = println("Nom...")
  }

  // This is what it does...
  val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  */

  class Person(name: String) {

    def sayHi: Unit = {
      println(s"Hey my name is ${name}, how can I help?")
    }

    val jim = new Person(name = "Jim") {
      override def sayHi: Unit = {
        println("Hi my name is Jim, how can I be of service?")
      }
    }
  }

  /*

  Exercises:

  1). Create a Generic trait MyPredicate[T] - which will contain a method that checks for a condition
    - Test method takes element of type T & returns a boolean

  2). Create a Generic trait MyTransformer[A, B] - which will contain a method of type A into type B

  3). MyList:
    - map(MyTransformer) & returns a MyList of a different type.
    - filter(MyPredicate) & returns a MyList of the same type.
    - flatmap(transformer from A to MyList[B]) => MyList[B]

  */

}
