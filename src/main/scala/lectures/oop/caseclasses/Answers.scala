package lectures.oop.caseclasses

import lectures.oop.generics.{Cons, Empty}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformation[-A, B] {
  def transformer(transform: A): B
}

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = s"[${printElements}]"

  def map[B](transformer: MyTransformation[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatmap[B](transformation: MyTransformation[A, MyList[B]]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

case object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = {
    new Cons[B](h = element, t = Empty)
  }
  def printElements: String = ""

  def map[B](transformer: MyTransformation[Nothing, B]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def flatmap[B](transformation: MyTransformation[Nothing, MyList[B]]): MyList[B] = Empty

  // Concatenation function to help with flatMap implementation
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = {
    new Cons[B](h = element, t = this)
  }

  def printElements: String = {
    if (t.isEmpty) "" + h
    else s"${h} ${t.printElements}"
  }

  def map[B](transformer: MyTransformation[A, B]): MyList[B] = {
    new Cons(h = transformer.transformer(h), t = t.map(transformer))
  }

  /*

  This looks like the following:

  [1, 2, 3].map(n * 2) == new Cons(1 * 2, [2,3].map(n * 2)) == new Cons(1 * 2, new Cons(2 * 2, [3].map(n * 2))

  == new Cons(1 * 2, new Cons(2 * 2, new Cons(3 * 2, [].map(n * 2))))

  == new Cons (1 * 2, new Cons(2 * 2, new Cons(3 * 2, Empty))))

  == new Cons(2, new Cons(4, new Cons(6, Empty))))

  Then when we call .toString, we get... [2, 4, 6]

  */

  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*

  This looks like the following:

  [1, 2, 3].filter(n % 2 == 0) == [2, 3].filter(n % 2 == 0) == new Cons(2, [3].filter(n % 2 == 0)) == new Cons(2, Empty)

  */

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  /*

  [1, 2] ++ [3, 4, 5]

  == new Cons(1, [2] ++ [3, 4, 5])

  == new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))

  == new Cons(1, new Cons(2, [3, 4, 5]))

  == new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))

  */

  def flatmap[B](transformation: MyTransformation[A, MyList[B]]): MyList[B] = {
    transformation.transformer(h) ++ t.flatmap(transformation)
  }

  /*

  This looks like the following:

  [1, 2].flatMap(n => [n, n + 1])

  == [1, 2] ++ [2].flatMap(n => [n, n + 1])

  == [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])

  == [1, 2] ++ [2, 3] ++ Empty

  == [1, 2, 2, 3]

  */

}

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("World", Empty))
  println(listOfIntegers.toString)
  println(listOfString.toString)

  // Anonymous class - we have implemented upon instanciation of the trait
  println(listOfIntegers.map(new MyTransformation[Int, Int] {
    override def transformer(transform: Int): Int = transform * 2
  }).toString) // Prints [2, 4, 6]

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = if (elem % 2 == 0) true else false
  }).toString) // Prints [1, 2, 3]

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatmap(new MyTransformation[Int, MyList[Int]] {
    override def transformer(transform: Int): MyList[Int] = new Cons(transform, new Cons(transform + 1, Empty))
  }).toString)

  println(listOfIntegers == cloneListOfIntegers) // Now returns true!
  // This is a HUGE benefit when dealing with collections / lists, otherwise we would have had to define a recursive
  // Equals function that would compare each element!
}
