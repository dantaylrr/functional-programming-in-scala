package lectures.oop.abstraction

import scala.annotation.tailrec

abstract class MyList {

  /*

  Aside >> A linked-list is a data structure that consists of nodes where each node contains a data field & a reference
  link to the next node in the list

  e.g.

  HEAD -> element1(data, next) -> element2(data, next) -> element3(data, next) -> ... -> elementN(data, next) -> NULL / EMPTY

  Singly linked-list that will contain Integers & the following methods

  1). head() -> Returns the first element of the list

  2). tail() -> Returns the tail of the list of class MyList

  3). isEmpty -> Returns a boolean true if the list is empty

  4). add -> Returns a new list with an element added

  5). toString -> A string representation of the list

  */

  // There are quite a few ways to achieve the above, outlined below is 1 possible solution that most likely isn't
  // the correct way to go about this, but does achieve the desired outcome - it uses a lot of syntax & methods that we haven't yet explored
  // such as... List.head, List.tail, New list construction using :: etc.

  // Dan T solution - assumes list input rather than list construction from nothing, which wasn't the desired outcome from this exercise

  def getHead: Int
  def getTail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElement: Unit
  def toString: String
}

class DefinitiveList(input: List[Int]) extends MyList {

  override def getHead: Int = input.head

  override def getTail: MyList = new DefinitiveList(input = input.tail)

  override def isEmpty: Boolean = {
    if (input.isEmpty) true
    else false
  }

  override def add(element: Int): MyList = {
    new DefinitiveList(input = element :: input)
  }

  override def printElement: Unit = println(s"Here is the list ${input}.")

  override def toString: String = {
    @tailrec
    def convertHeadToString(h: Int, t: List[Int], acc: String = "["): String = {
      if (t.isEmpty) acc + s"${h}]"
      else convertHeadToString(h = t.head, t = t.tail, acc = acc + s"${h}, ")
    }

    convertHeadToString(h = input.head, t = input.tail)
  }
}

/* object Testing extends App {
  val list = new DefinitiveList(input = List(1, 2, 3))
  println(list.getHead)
  println(list.getTail)
  println(list.isEmpty)
  val newList = list.add(1)
  println(newList)
  newList.printElement
  println(newList.toString)
} */

  // Outlined below is the solution given by the lecturer & is most likely the optimal way to achieve the above using Linked Lists only

  // Lecturer solution using knowledge of a linked list & list construction from scratch

abstract class LinkedList {

  def head: Int
  def tail: LinkedList
  def isEmpty: Boolean
  def add(int: Int): LinkedList
  def printElements: String
  override def toString: String = s"[${printElements}]"

}

// Empty-list & non-empty list

// Empty list will be an object as it in a singleton

object EmptyList extends LinkedList {

  def head: Int = throw new NoSuchElementException

  def tail: LinkedList = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(int: Int): LinkedList = {
    new Cons(h = int, t = EmptyList)
  }

  def printElements: String = ""
}

class Cons(h: Int, t: LinkedList) extends LinkedList {

  def head: Int = h

  def tail: LinkedList = t

  def isEmpty: Boolean = false

  def add(int: Int): LinkedList = {
    new Cons(h = int, t = this)
  }

  def printElements: String = {
    if (t.isEmpty) "" + h
    else s"${h} ${t.printElements}"
  }
}

object TestingLinkedList extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, EmptyList))) // This is a linked list (1, 2, 3)
  println(list.tail.head) // Prints out the value 2
  println(list.add(4).head) // Should print 4 as it adds the element to the head
  println(list.toString)
}