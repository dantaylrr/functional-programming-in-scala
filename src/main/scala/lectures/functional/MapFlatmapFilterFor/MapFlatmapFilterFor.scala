package lectures.functional.MapFlatmapFilterFor

object MapFlatmapFilterFor extends App {

  // Introducing the official versions of map, flatmap, filter, collections

  val list: List[Int] = List(1, 2, 3)
  println(list)
  println(list.head) // These are built into scala collections
  println(list.tail)

  // HOFs
  println(list.map(_ + 1)) // Official non-sugar implementation == list.map(x => x + 1)
  println(list.filter(_ % 2 == 0)) // Official non-sugar implementation == list.filter(x => x % 2 == 0)

  // Using an anonymous function for flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // Print out all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val characters = List('A', 'B', 'C')
  val colours = List("Black", "White")

  val concat = (x: Int, y: List[Char]) => y.map(y => s"${x}${y}")
  println(numbers.flatMap(concat(_, characters)))

  // This lays out good foundations for dealing with "loops" in Scala... If we have 1 loop = map, 2 loops = flatmap with a map,
  // 3 loops = flatmap, flatmap, map... and so on

  // This perhaps isn't the cleanest way to do this... but by breaking the problem down into different functions & lambda's, we can
  // slowly start to put together the solution...
  val concat2 = (x: String, y: List[String]) => y.map(y => s"${x}${y}")
  println(numbers.flatMap(concat(_, characters)).flatMap(concat2(_, colours)))

  // Let's implement both of the above in 1 line...
  println(numbers.flatMap(x => characters.map(y => s"${x}${y}"))) // Is the exact same as line 25 & 26... except a tad nicer syntactically & we define the lambda inside of map
  println(numbers.flatMap(x => characters.flatMap(y => colours.map(z => s"${x}${y}${z}")))) // Is the exact same as line 33 & 34... nicer syntactically & we define the lambda inside of map
  // These however, aren't really easy for someone to follow...

  // These are called ITERATIONS

  // Let's talk about foreach... this takes a function (similar to map) & returns a unit
  list.foreach(println)

  // Let's introduce for-comprehensions to tidy up some of the iterations we introduced above
  val forCombinations = for {
    x <- numbers if x % 2 == 0 // <- We can also filter out specific values here using a "guard" - these are translated into a filter call!
    y <- characters
    z <- colours
  } yield (s"${x}${y}${z}") // All of these are mapped into flatmaps & maps!
  println(forCombinations) // Prints out all of our combinations

  for {
    n <- numbers
  } println(n)

  // A syntax overload
  list.map {x =>
    x * 2
  } // Is also used sometimes

  /*

  1). Does MyList support for comprehensions?

  2). A small collection of at MOST one element - Maybe[+T]
      - map, flatmap & filter

  */
}
