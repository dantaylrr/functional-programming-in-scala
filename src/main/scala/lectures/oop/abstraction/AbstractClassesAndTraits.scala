package lectures.oop.abstraction

object AbstractClassesAndTraits extends App {

  // Classes which contain unimplemented values or methods are called Abstract
  abstract class Animal {
    // An abstract value & method - we want subclasses to define them for us
    val creatureType: String
    def eat: Unit
  }

  // val animal = new Animal -> Throw a compiler error - our abstract methods have not been defined

  class Dog extends Animal {
    // We can specify "override" key word here, but we do not really need to, the compiler will figure that out for us
    override val creatureType: String = "K9"

    override def eat: Unit = println("Crunch crunch...")
  }

  // Let's learn about traits

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  // Traits can be inherited alongside classes
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    // Override our Animal creature type
    override val creatureType: String = "Croc"
    // Override our animal eat method
    override def eat: Unit = println("Nom...")
    // Override our Carnivore eat method
    override def eat(animal: Animal): Unit = println(s"I'm a Croc & I'm eating a ${animal.creatureType}.")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /*

  Both abstract classes & traits can contain both abstract & non-abstract members...

  So what is the difference between abstract classes & traits?

  1). Traits can not have constructor parameters -> we cannot pass anything into a trait

  2). You can only extend 1 class, but can inherit multiple traits, by the same class

  3). Traits describe a type of behaviour... an abstract class is a type of thing...

  Abstract classes describe Animals... Trait's describe what these animals do...

  */

}
