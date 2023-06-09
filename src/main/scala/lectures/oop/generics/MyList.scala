package lectures.oop.generics

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = s"[${printElements}]"

}

object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = {
    new Cons(h = element, t = Empty)
  }
  def printElements: String = ""

}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def head: A = h

  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = {
    new Cons(h = element, t = this)
  }
  def printElements: String = {
    if (t.isEmpty) "" + h
    else s"${h} ${t.printElements}"
  }
}

object Testing extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfIntegers.tail.head)
  val listOfString = new Cons("Hello", new Cons("World", Empty))
  println(listOfString.head)
  println(listOfString.tail.head)
  println(listOfIntegers.toString)
}
