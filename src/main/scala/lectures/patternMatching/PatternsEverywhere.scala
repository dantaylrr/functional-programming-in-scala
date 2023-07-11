package lectures.patternMatching

object PatternsEverywhere extends App {

  // Big idea #1
  try {
    // Some code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "NPE"
    case _ => "Something else."
  }

  // Catches are actually matches (under the hood) - this makes sense

  // Big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  // Generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // Case classes, :: operators etc. all also work

  // Big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // a = 1, b = 2, c = 3
  // Multiple value definitions based on pattern matching

  val head :: tail = list
  println(head)
  println(tail)

  // Big idea #4
  // A partial function - each element is passed through the cases
  val mappedList = list.map {
    case v if v % 2 == 0 => s"$v is even."
    case 1 => "The one."
    case _ => "Something else."
  }

  // Is the same as...
  val mappedList2 = list.map {
    { x => x match
        case v if v % 2 == 0 => s"$v is even"
        case 1 => "The one"
        case _ => "Something else"
    }
  }

  println(mappedList) // List(The one, 2 is even, Something else, 4 is even)
}
