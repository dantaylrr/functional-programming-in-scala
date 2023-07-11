package lectures.patternMatching

import java.security.KeyStore.TrustedCertificateEntry
import scala.util.Random

object PatternMatching extends App {

  // Like a switch (in C++)
  val random = new Random()
  val x = random.nextInt(10) // Any number from 0 to 10
  // This is called pattern matching
  val description = x match {
    case 1 => "The one."
    case 2 => "Double or nothing."
    case 3 => "Third time is the charm."
    case _ => "Something else." // _ is a wildcard
  }

  println(x)
  println(description)

  // Important functionality of pattern-matching

  // 1). Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob.match {
    case Person(n, a) if a < 21 => s"Hi, my name is ${n} & I am ${a} years old & I can't drink in the US!" // We can also add "guards" - such as if statements
    case Person(n, a) => s"Hi, my name is ${n} & I am ${a} years old!"
    case _ => "I don't know who I am!"
  }

  println(greeting)

  /*

  1). Cases are matched in order.
  2). What if no cases match (i.e. we don't use our wildcard case)? -> We get a "MatchError"
  3). What's the type of the pattern-match expression? It is the unification of all return types! If all string, return = string, if different, return = Any
  4). Pattern-matching works extremely well with case classes (as they come with extractor patterns out of the box).

  */

  // Pattern-matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(b) => println(s"Matched a dog of the ${b} breed.")
  }

  // People can try to match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  // This is overkill!!!
  println(isEven)

  /*

  Exercises:

  1). Simple function that uses pattern-matching:

  Takes an Expression & returns the human readable form of it.

  e.g.
  Sum(Number(2), Number(3)) => 2 + 3
  Product(Number(2), Number(3)) => 2 * 3
  Sum(Product(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
  Product(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3

  */

  // 1).
  trait Expression
  case class Number(n: Int) extends Expression
  case class Sum(e1: Expression, e2: Expression) extends Expression
  case class Product(e1: Expression, e2: Expression) extends Expression

  // Functional design - using recursive function
  def show(e: Expression): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Product(e1, e2) => {
      def maybeShowParentheses(exp: Expression) = exp match {
        case Product(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  // Testing
  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Product(Sum(Number(2), Number(1)), Number(3))))
  println(show(Product(Sum(Number(2), Number(1)), Sum(Number(3), Number(4)))))
  println(show(Sum(Product(Number(2), Number(1)), Number(3))))
}
