package playground

import scala.annotation.tailrec

object Exercises extends App{

  def sumList(input: List[Int]): Int = {
    @tailrec
    def auxFunc(counter: Int = 0, list: List[Int]): Int = {
      if (list.isEmpty) counter
      else auxFunc(counter = counter + list.head, list = list.tail)
    }

    auxFunc(list = input)
  }

  println(sumList(input = List(1, 2, 3, 4, 5)))

}
