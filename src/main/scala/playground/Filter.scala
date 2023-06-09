package playground

abstract class MyList(input: List[Int] = List()) {

  // We need a class field here as we want to refer to the input
  val value: List[Int] = this.input

  val property: String

}

object MyList {

  def checkForEvens(input: List[Int]): Boolean = {
    def auxEvenChecker(h: Int = input.head, t: List[Int] = input.tail): Boolean = {
      if (h % 2 == 0) true
      else false && auxEvenChecker(h = t.head, t = t.tail)
    }
    auxEvenChecker()
  }

  def checkForOdds(input: List[Int]): Boolean = {
    def auxOddChecker(h: Int = input.head, t: List[Int] = input.tail): Boolean = {
      if (h % 2 != 0) true
      else false && auxOddChecker(h = t.head, t = t.tail)
    }
    auxOddChecker()
  }

  def constructEvenList(input: List[Int]): MyList = {
    new EvenListClass(input = input.filter(output => (output % 2 == 0)))
  }

  def constructOddList(input: List[Int]): MyList = {
    new OddListClass(input = input.filter(output => (output % 2 != 0)))
  }

  def constructor(input: List[Int]): MyList = {
    if (checkForOdds(input) == true) constructOddList(input)
    else if (checkForEvens(input) == true) constructEvenList(input)
    else new EmptyListClass(input)
  }

  val whatIsThis: String = "It's a List."

}

trait EvenList {
  val property = "even"
}

trait OddList {
  val property = "odd"
}

class EvenListClass(input: List[Int]) extends MyList(input: List[Int]) with EvenList {

}

class OddListClass(input: List[Int]) extends MyList(input: List[Int]) with OddList {

}

class EmptyListClass(input: List[Int]) extends MyList(input: List[Int]) with OddList {

}

object TestingForEach extends App {

  val list = MyList
  println(list.whatIsThis)
  val evenList = list.constructor(List(1, 2, 3, 4, 5, 6, 7, 8))
  println(evenList.property)
  println(evenList.value)
  val oddList = list.constructor(List(1, 2, 3, 4, 5, 6, 7, 8))
  println(oddList.property)
}
