package lectures.oop.basics

object AnswerCounter extends App {

  // Testing our class, creating new class instances to keep track of the counter value
  val counter = new Counter(start = 1)
  // +1 standard increment
  val counterInc = counter.inc()
  // +10 overloaded increment
  val overloadedCounterInc = counterInc.inc(increment = 100)
  // -1 standard decrement
  val counterDec = overloadedCounterInc.dec()
  // -10 overloaded decrement
  val overloadedCounterDec = counterDec.dec(decrement = 100) // Returns us back to the start

}

class Counter(val start: Int) {

  def currentCount(): Unit = {
    println(s"Here is the current count: ${start}")
  }

  // Print to console on instantiation
  currentCount()

  // Overloading the incremental counter
  def inc(): Counter = {
    new Counter(start = start + 1) // This is known as immutability - we create a new class instance
  }

  def inc(increment: Int): Counter = {
    new Counter(start = start + increment)
  }

  // Overloading the decremental counter
  def dec(): Counter = {
    new Counter(start = start - 1)
  }

  def dec(decrement: Int): Counter = {
    new Counter(start = start - decrement)
  }

}
