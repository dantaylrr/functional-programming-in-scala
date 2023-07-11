package lectures.patternMatching

import lectures.oop.caseclasses.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // 1). Constants
  val x: Any = "Scala!"
  val constans = x match {
    // Cases can be:
    // A Number
    case 1 => "A Number."
    // A String
    case "Scala!" => "The Scala."
    // A Boolean
    case true => "The truth!"
    // A Singleton Object
    case AllThePatterns => "A singleton object!"
  }

  // 2.1). Match anything -> wildcards
  val matchAnything = x match {
    case _ =>
  }

  // 2.2). A variable
  val matchVariable = x match {
    case something => s"I've found ${something}!"
  }

  // 3). Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found ${something}!"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // Pattern matches can be nested!

  // 4). Case classes - a constructor pattern
  // Pattern matches can be nested with case classes as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, Cons(subhead, subtail)) =>
  }

  // 5). List patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // This is called a list extractor & is very powerful!
    case List(1, _*) => // A list of arbitrary length
    case 1 :: List(_) => // Called an infix pattern
    case List(1, 2, 3) :+ 42 => // Also an infix pattern
  }

  // 6). Type specifiers - force pattern matching to conform to specific types
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // Explicit type specifier
    case _ =>
  }

  // 7). Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // A name binding, names a pattern
    case Cons(1, rest @ Cons(2, _)) => // Name binding inside nested patterns
  }

  // 8). Multi-patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => // A compound or multi-pattern... | = OR
  }

  // 9). If guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // Question.....

  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "A list of Strings!"
    case listOfNumber: List[Int] => "A list of Numbers!"
    case _ => ""
  }

  println(numbersMatch) // Gives us "A list of Strings!" - Why?!
  // This is a JVM trick question - because of JVM backwards compatibility & generics being introduced later on,
  // the above is compiled to...

  // This is called type erasure

  val numbersMatch2 = numbers match {
    case listOfStrings: List[Any] => "A list of Strings!"
    case listOfNumber: List[Any] => "A list of Numbers!"
    case _ => ""
  }
}
