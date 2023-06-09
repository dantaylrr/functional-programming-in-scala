package lectures.oop.inheritance

object Inheritance extends App {

  // Single class inheritance
  sealed class Animal {
    /*protected*/ def eat(): Unit = println("Nom nom...")

    val creatureType: String = "Wild"
  }

  // Extends is used to build / inherit parent functionality
  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("Crunch crunch ...")
    }
  }

  // When we instantiate a new instance of Cat, we can access Animal methods
  val cat = new Cat
  cat.crunch()
  println(cat)

  /*

  Cat is called a sub-class of Animal & Animal is called a parent class

  Note - if our "eat" method was defined as private, then our Cat instance would not be able to access it, only Animal classes can

  You can also use the "protected" key word, this marks a method or value as only usable within the parent class & any sub-classes

  Note - you still cannot call the method "eat" from the class instantiation, only from within the sub-class itself

  How does this affect constructors?

  */

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  // class Adult(name: String, age: Int, idCard: String) extends Person -> This will not compile
  // The JVM needs to call constructors of Person before it can call the constructors for Adult
  class Adult(name: String, age: Int, idCard: String) extends Person(name: String, age: Int)

  // In the case above for the auxillary function this(), where we set age = 0... we can omit the "age" parameter from
  // the "extends Person(...) part, because the compiler can find the parameter age being set

  // What about method overriding?

  // We can use the "override" key word
  class Dog(override val creatureType: String) extends Animal {
    override def eat(): Unit = {
      println("Bite bite...")
      // What if we want to also call the eat() method from the class Animal we have inherited?
      // This is where we can call the "super" key word
      super.eat()
    }

    // override val creatureType: String = "Domestic" -> We can override any values here with a static string, or in the class constructor with an input
  }

  val dog = new Dog(creatureType = "Domestic")
  dog.eat()
  println(dog.creatureType)

  /*

  As things stand, all instances of Dog will call the overriding class method for eat()

  What if we want to implement logic that determines which method for eat() we want to call?

  We can use a method called "type substitution"

  */

  // We can define an Animal, but using the Dog class - this is called Polymorphism... the most overridden method will always be the one to call
  // If we have a collection of Cats & Dogs, each method of eat will be the correctly called one, all others will have the base method for eat
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat()

  // Note we have to remove the "protected" from definition eat() for the Animal class to access it here

  /*

  Now let's talk about "super"

  Used when you want to reference a method or value from a parent class

  Using our Dog class & the eat() method

  We can use super.eat()

  ...

  What if we want to prevent overrides?

  There are multiple ways we can do this:

  1). Use the "final" key word before our definitions

  2). "final" can also be used on the Class itself - preventing the class from being extended

  3). We can "seal" the class - this is a softer restriction, it means:
      *. You can extend the class in this file only

  */

}
