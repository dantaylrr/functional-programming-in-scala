package lectures.functional.MapFlatmapFilterFor


// Useful to denote the possible absence of a value, rather than just a collection of 1 element
abstract class Maybe[+T] {

  def map[A](transformer: T => A): Maybe[A]
  def filter(predicate: T => Boolean): Maybe[T]
  def flatMap[A](transformation: T => Maybe[A]): Maybe[A]

}

case object EmptyMaybe extends Maybe[Nothing] {

  def map[A](transformer: Nothing => A): Maybe[A] = EmptyMaybe

  def filter(predicate: Nothing => Boolean): Maybe[Nothing] = EmptyMaybe

  def flatMap[A](transformation: Nothing => Maybe[A]): Maybe[A] = EmptyMaybe

}

case class Just[+T](value: T) extends Maybe[T] {

  def map[A](transformer: T => A): Maybe[A] = {
    new Just[A](value = transformer(value)) // Create a new instance of Just with our transformation applied
  }

  def filter(predicate: T => Boolean): Maybe[T] = {
    if (predicate(value)) this // If the filter condition passes return the original Just object
    else EmptyMaybe
  }

  def flatMap[A](transformation: T => Maybe[A]): Maybe[A] = {
    transformation(value) // Just return the transformation applied to the value
  }
}

object MaybeTest extends App {
  val just3 = new Just(3)
  println(just3)
  println(just3.map((x:Int) => x * 3))
  println(just3.flatMap(x => Just(x % 2 == 0)))
  println(just3.filter((x: Int) => x % 2 == 0))
}
