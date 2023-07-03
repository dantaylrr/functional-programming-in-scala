package lectures.functional.TuplesAndMaps

object TuplesAndMaps extends App {

  // Tuples = finite ordered lists
  val aTuple = Tuple2("Hello Scala", 2) // Tuple2[Int, String] == (Int, String)

  // Tuples can group at most, 22 values of different types
  println(aTuple._1) // To access members
  println(aTuple.copy(_2 = "Goodbye Scala"))
  println(aTuple.swap) // (2, Hello Scala)

  // Maps are collections used to associate keys to values
  val aMap: Map[String, Int] = Map(("hello", 2))

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789) // Using -> is syntactic sugar for the same tuple
  println(phoneBook)

  // Map operations
  println(phoneBook.contains("Jim")) // Returns a boolean
  println(phoneBook("Jim")) // Returns the value at key jim

  // To prevent NoSuchElement exceptions when calling a key that doesn't exist, it's good to create a map with the .withDefaultValue() method
  // Or throw an exception
  val betterPhoneBook = phoneBook.withDefaultValue(-1)
  println(betterPhoneBook("Mary")) // Now returns -1

  val newPairing = "Mary" -> 123
  val newPhoneBook = betterPhoneBook + newPairing
  println(newPhoneBook)

  // Functions maps
  // Maps take pairings
  println(newPhoneBook.map(pair => pair._1.toLowerCase() -> pair._2)) // We are just lower-casing the keys

  // filterKeys
  println(newPhoneBook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(newPhoneBook.view.mapValues(number => number * 10).toMap)

  // Conversions
  println(newPhoneBook.toList) // List(("Jim", 555), ("Daniel", 789), ...)
  println(List("Daniel" -> 789).toMap)

  // Group by
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))

  /*

  Exercises:

  1). What would happen if we had two original entries... "Jim" -> 555 & "JIM" -> 900 & we ran the "toLowerCase" function?

  2). Design an overly simplified social network based on maps
      Person = String (name)
      - We want to add a person to the network
      - Remove a person
      - Add a person as a friend (mutual)
      - Unfriend (mutual)
      - Number of friends of a given person
      - Person with the most friends
      - How many people have no friends?

  */
}
