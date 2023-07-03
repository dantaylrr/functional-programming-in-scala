package lectures.functional.TuplesAndMaps

object Answers extends App {

  // 1).

  val ourMap = Map("Jim" -> 555, "JIM" -> 123)
  println(ourMap.map(pairing => pairing._1.toLowerCase() -> pairing._2)) // Map("jim" -> 123)
  // Looks like we take the last value present in the map

  // 2).
  // Define class of Person
  case class Person(name: String)

  val dan = Person("Dan")
  val hannah = Person("Hannah")
  val jim = Person("Jim")

  // Define our network
  val network: Map[Person, List[Person]] = Map()

  // Add a person to the network
  def addToNetwork(network: Map[Person, List[Person]], person: Person): Map[Person, List[Person]] = {
      network + (person -> List())
    }

  // Remove someone from a network
  def removeFromNetwork(network: Map[Person, List[Person]], person: Person): Map[Person, List[Person]] = {

    // We cannot simply filter the map as we will leave some friendships "hanging" with a person that doesn't exist
    def removeAux(friends: List[Person], networkAccumulator: Map[Person, List[Person]]): Map[Person, List[Person]] = {
      if (friends.isEmpty) networkAccumulator
      else removeAux(friends.tail, unFriending(networkAccumulator, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  // Add as a friend
  def addFriend(network: Map[Person, List[Person]], person1: Person, person2: Person): Map[Person, List[Person]] = {

    // Typically we would wrap these in try/except clauses of if statements

    // Start by getting the list of friends
    val friends1: List[Person] = network(person1)
    val friends2: List[Person] = network(person2)

    // Update each friend list
    val updatedFriends1: List[Person] = friends1 :+ person2
    val updatedFriends2: List[Person] = friends2 :+ person1

    // Add each updated friend list back to the original network
    network + (person1 -> updatedFriends1) + (person2 -> updatedFriends2)
  }

  def unFriending(network: Map[Person, List[Person]], person1: Person, person2: Person): Map[Person, List[Person]] = {

    // Typically we would wrap these in try/except clauses of if statements

    // Start again by getting the friends list
    val friends1: List[Person] = network(person1)
    val friends2: List[Person] = network(person2)

    // Pop each list with the other person
    val updatedFriends1: List[Person] = friends1.filter(p => p != person2)
    val updatedFriends2: List[Person] = friends2.filter(p => p != person1)

    // Add each updated friend list back to the original network
    network + (person1 -> updatedFriends1) + (person2 -> updatedFriends2)
  }

  // Testing add to network
  val networkAdd = addToNetwork(network, dan)
  val networkAdd2 = addToNetwork(networkAdd, hannah)
  println(networkAdd2)

  // Testing remove from network
  val networkRemove = removeFromNetwork(networkAdd2, dan)
  println(networkRemove)

  // Testing add as friend
  val addSomeFriends = addFriend(networkAdd2, hannah, dan)
  println(addSomeFriends)

  // Testing remove friend
  val removeSomeFriends = unFriending(addSomeFriends, hannah, dan)
  println(removeSomeFriends)

  // Testing remove from network
  val removed = removeFromNetwork(removeSomeFriends, hannah)
  println(removed)

  // Jim, Dan & Hannah
  val people = addToNetwork(addToNetwork(addToNetwork(network, dan), hannah), jim)
  val danHan = addFriend(people, dan, hannah)
  val jimDan = addFriend(danHan, dan, jim)
  val finalNet = jimDan

  println(finalNet)

  def nFriends(network: Map[Person, List[Person]], person: Person): Int = {
    if (!network.contains(person)) 0
    else network(person).length
  }

  // How many friends do Dan & Hannah have?
  println(nFriends(finalNet, dan)) // 2
  println(nFriends(finalNet, hannah)) // 1

  def mostFriends(network: Map[Person, List[Person]]): Person = {
    // Get the max of a pair then return the first element from that pair
    network.maxBy(pairing => pairing._2.length)._1
  }

  // Get the person with the most friends
  println(mostFriends(finalNet))

  // Number of people with no friends
  def nPeopleWithNoFriends(network: Map[Person, List[Person]]): Int = {
    network.count(pair => pair._2.isEmpty)
  }

  // Get the number of people with no friends
  val bob = Person("Bob")
  val newFinalNet = addToNetwork(finalNet, bob)
  println(nPeopleWithNoFriends(newFinalNet)) // Prints 1
}
