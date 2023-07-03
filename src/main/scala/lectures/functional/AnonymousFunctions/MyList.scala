package lectures.functional.AnonymousFunctions

/*

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformation[-A, B] {
  def transformer(transform: A): B
}

*/

abstract class MyList[+A] {

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = s"[${printElements}]"

  // Higher-order functions... These either receive functions as parameters (transformer, predicate, flatmap... all defined at runtime below), or return functions as a result
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatmap[B](transformation: (A => MyList[B])): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

}

object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException
  def tail: Nothing = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = {
    new Cons[B](h = element, t = Empty)
  }
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatmap[B](transformation: Nothing => MyList[B]): MyList[B] = Empty

  // Concatenation function to help with flatMap implementation
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {

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

  def map[B](transformer: A => B): MyList[B] = {
    new Cons(h = transformer(h), t = t.map(transformer))
  }

  /*

  This looks like the following:

  [1, 2, 3].map(n * 2) == new Cons(1 * 2, [2,3].map(n * 2)) == new Cons(1 * 2, new Cons(2 * 2, [3].map(n * 2))

  == new Cons(1 * 2, new Cons(2 * 2, new Cons(3 * 2, [].map(n * 2))))

  == new Cons (1 * 2, new Cons(2 * 2, new Cons(3 * 2, Empty))))

  == new Cons(2, new Cons(4, new Cons(6, Empty))))

  Then when we call .toString, we get... [2, 4, 6]

  */

  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
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

  def flatmap[B](transformation: A => MyList[B]): MyList[B] = {
    transformation(h) ++ t.flatmap(transformation)
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
  val anotherListOfIntegers = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("World", Empty))
  println(listOfIntegers.toString)
  println(listOfString.toString)

  // Anonymous class - we have implemented upon instantiation of the trait

  // 1).
  println(listOfIntegers.map(transform => transform * 2).toString) // Prints [2, 4, 6]

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString) // Prints [1, 2, 3]

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatmap(transform => new Cons(transform, new Cons(transform + 1, Empty))).toString)
  // Cannot use the underscore more than once as we use elem for than once

}
