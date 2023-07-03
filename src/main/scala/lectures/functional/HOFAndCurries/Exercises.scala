package lectures.functional.HOFAndCurries

object Exercises extends App {

  /*

  1). Expand MyList with some methods:

     - forEach -> for each element print a side effect.
        A => Unit is applied to every element of MyList
        [1, 2, 3].foreach(x => println(x)) = 1  2  3
     - sort -> receives a sorting function that compares two functions
        ((B, C) => Int) => MyList[C]
        [1, 2, 3].sort((x, y) => y - x => [3, 2, 1]
     - zipWith -> Takes a list and zips them together
        (list, (A, A) => B) => MyList[B]
        [1, 2, 3].zipWith([4, 5, 6], x * y) => [1 * 4, 2 * 5, 3 * 6]
     - fold -> Curried function that returns a value
         fold(start)(function) => a value
         [1, 2, 3].fold(0)(x + y) = 6

  2). 
  
  toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
  fromCurry(f: (Int => Int => Int)) => (Int, Int)f => Int
  
  3).
  
  compose(f, g) => x => f(g(x))
  andThen(f, g) => x => g(f(x))
   */

    // 2).

    def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
      (x: Int) => (y: Int) => f(x, y)
    }

    def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
      (x: Int, y: Int) => f(x)(y)
    }

    // FunctionX
    def compose[A, B, T](f: A => B, g: T => A): T => B = {
      x => f(g(x))
    }

    def composeInt(f: Int => Int, g: Int => Int): Int => Int = {
      x => f(g(x))
    }

    def andThen[A, B, T](f: T => A, g: A => B): T => B = {
      x => g(f(x))
    }

    def andThenInt(f: Int => Int, g: Int => Int): Int => Int = {
      x => f(g(x))
    }

    // Testing a few bits
    def superAdder2: (Int => Int => Int) = toCurry(_ + _)

    println(superAdder2(5)(28)) // Should print 33

    def simpleAdder: (Int, Int) => Int = fromCurry(superAdder2)

    println(simpleAdder(5, 28))

    val add2 = (x: Int) => x + 2
    val times3 = (x: Int) => x * 3

    val composed = compose(add2, times3)
    val ordered = andThen(add2, times3)

    println(composed(4)) // Prints 14
    println(ordered(4)) // Prints 18
}
