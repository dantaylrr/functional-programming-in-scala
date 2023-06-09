package lectures.oop.generics

import lectures.oop.generics.Generics.InvariantList

object Generics extends App {

  /* We previously defined a linked-list application

  However, this implementation only works with Integers, what if we want to make it even more generic &

  for example, wanted to generate a linked list of different types

  */

  // Here, A defines a generic class-type, which we can refer to throughout
  class MyList[A] {
    // Use the type A
  }

  val listOfIntegers = new MyList[Int] // <- The type Int will replace the generic type A for this instance of the class
  val listOfStrings = new MyList[String]

  class MyMap[Key, Value] {
    // Do something with our Key & Value
  }

  trait aTrait[A] {
    // A trait
  }

  // What about generic methods? Let's start with the companion object for MyList

  object MyList {

    // Method signature for a generic method of type A
    def empty[A]: MyList[A] = {
      ???
    }
    val emptyListOfIntegers = MyList.empty[Int]
  }

  // The variance problem
  class Animal

  // Can you spot the problem with class hierarchy?
  // If Cat extends Animal, does a list of Cat also extend a list of Animal?
  class Cat extends Animal
  class Dog extends Animal

  /* 3 possible answers:

  1). Yes, List[Cat] extends List[Animal] <- Called COVARIANCE */

  class CovariantList[+A] // Denotes a covariant list
  val animal: Animal = new Cat
  // We can replace a list of animals with a list of cats because cats are animals
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // Can I add any Animal to animalList? i.e. animalList.add(new Dog)??? <- this will pollute the list - we should return a list of Animals!

  /*

  2). No, List[Cat] & List[Animal] are two separate entities <- Called INVARIANCE

  */

  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = InvariantList[Animal]

  /*

  3). Absolutely not <- Called CONTRAVARIANCE

  */

  class ContravariantList[-A]
  // We can replace a list of Cats with a list of Animals because all Animals are Cats (not the case, Animals can also be Dogs)
  val contravariantAnimalList: ContravariantList[Cat] = new ContravariantList[Animal]

  // If we define the below, it starts to make a bit more sense... a trainer of an Animal can also be a trainer of a Cat...
  // Contrary to our example above, if we do the Covariant example of this... a trainer of Cat can also be a trainer of Animal
  // This makes no sense, how can a Cat trainer be expected to train a Dog that is also of type Animal?
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // Now let's talk about bounded types.

  // Class Cage only takes types A, that are a sub-class of Animal!
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(animal = new Cat)

  /* class Car
  val newCage = new Cage(animal = Car) <- This is not accepted! */

  // Let's revisit out MyList class

  class MyCollection[+A] { // Classes should be Covariant, typically

    def add[B >: A](element: B): MyCollection[B] = { // Let's use A = Cat, B = Dog == Animal
      // So, the above implementation suggests... If I add a Dog, to a list of Cats, then I get back a list of Animals...
      // A List of Animals = A list of Cats, but if I add a Dog to it, it turns back into a List of Animals
      // Notice here that B = Dog is actually a Super class of A = Cat, because a Dog is also an Animal which is > Cat. Complicated!
      ???
    }
  }

  // Exercise - expand MyList to be Generic

}
