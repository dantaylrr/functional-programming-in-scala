package lectures.functional.MapflatMapFilterFor

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

  // Higher-order functions... These either receive functions as parameters (transformer, predicate, flatMap... all defined at runtime below), or return functions as a result
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformation: (A => MyList[B])): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B]

  // HOFs
  def forEach(f: A => Unit): Unit
  def sort(f: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](start: B)(f: (B, A) => B): B
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
  def flatMap[B](transformation: Nothing => MyList[B]): MyList[B] = Empty

  // Concatenation function to help with flatMap implementation
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // HOFs
  def forEach(f: Nothing => Unit): Unit = ()
  def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = Empty
  def fold[B](start: B)(f: (B, Nothing) => B): B = start

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

  def flatMap[B](transformation: A => MyList[B]): MyList[B] = {
    transformation(h) ++ t.flatMap(transformation)
  }

  /*

  This looks like the following:

  [1, 2].flatMap(n => [n, n + 1])

  == [1, 2] ++ [2].flatMap(n => [n, n + 1])

  == [1, 2] ++ [2, 3] ++ Empty.flatMap(n => [n, n + 1])

  == [1, 2] ++ [2, 3] ++ Empty

  == [1, 2, 2, 3]

  */

  //HOFs
  def forEach(f: A => Unit): Unit = {
    f(head)
    tail.forEach(f)
  }

  def sort(f: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (f(x, sortedList.head) <= 0) new Cons[A](x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(f)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B],f: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length.")
    else new Cons(f(h, list.head), t.zipWith(list.tail, f))
  }

  override def fold[B](start: B)(f: (B, A) => B): B = {
    // val newStart = f(start, h)
    t.fold(f(start, h))(f)
  }

  /*

  [1, 2, 3].fold(0)(x + y)
  == [2, 3].fold(1)(x + y)
  == [3].fold(3)(x + y)
  == [].fold(6)(x + y) == [Empty].fold(x + y) == start == 6

  */

}

object ListTest extends App {
  val listOfIntegers = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(listOfIntegers.tail)
  val anotherListOfIntegers = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("World", Empty))
  println(listOfIntegers.toString)
  println(listOfString.toString)

  // Anonymous class - we have implemented upon instantiation of the trait

  // 1).
  println(listOfIntegers.map(transform => transform * 2).toString) // Prints [2, 4, 6]

  println(listOfIntegers.filter(elem => elem % 2 == 0).toString) // Prints [1, 2, 3]

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(transform => new Cons(transform, new Cons(transform + 1, Empty))).toString)
  // Cannot use the underscore more than once as we use elem for than once

  // Testing forEach

  val printer: Int => Unit = (element: Int) => println(element)
  listOfIntegers.forEach(printer)

  val sorter: (Int, Int) => Int = (x: Int, y: Int) => y - x
  println(listOfIntegers.sort(sorter).toString)

  val concatenator: (Int, String) => String = (a: Int, b: String) => s"${a}${b}"
  println(anotherListOfIntegers.zipWith(listOfString, concatenator))

  val folder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  println(listOfIntegers.fold(0)(folder))

  // For comprehensions

  val combinations = for {
    n <- listOfIntegers
    s <- listOfString
  } yield s"${n}${s}"
  println(combinations)

}
