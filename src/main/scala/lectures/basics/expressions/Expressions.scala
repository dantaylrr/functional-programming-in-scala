package lectures.basics.expressions

object Expressions extends App {
  /* 1 + 2 here is called an expression.*/
  val x = 1 + 2
  println(x)

  /* Scala has the following math operators:
     +  -  *  /  &  |  ^  <<  >>  >>> (right shift with zero extension) */

  // Logical / Boolean operators
  println(1 == x)
  // !=   >   >=   <  <=

  var aVariable = 2
  aVariable += 3 // this also works with -= *= /=
  // these are all side effects

  /* Instructions vs Expressions
     Something we tell the computer to DO... vs Something with a VALUE or TYPE */

  // IF expression
  val aCondition = false
  val aConditionedValue = if (aCondition) 5 else 3
  print(aConditionedValue)

  /* for & while loops are generally discouraged in Scala.
     This is because they typically only compute side-effects. */

  var i = 0
  while (i < 10) {
    println(i)
    i += 1 // these are all side-effects
  }

  // AVOID THIS - IT IS IMPERATIVE CODE.

  // Scala forces everything to be an expression.

  val aWeirdValue = (aVariable = 3)
  /* this is a Unit type - equivalent to Java's VOID.
     this basically means nothing of meaning is returned */
  println(aWeirdValue) // returns ()
  println(aVariable) // now returns 3

  // examples of side-effects... println(), while, reassigning

  // Code blocks:

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "Hello" else "Goodbye"
  } // the value of the whole block is the value of the last expression, hence this is a string.

  // y & z only exist inside the code block, they are not publicly accessible

  // Exercises:

  /*
  1). What's the difference between "hello world" & println("hello world")

  2). Evaluate: */

  val someValue = {
    2 < 3
  }

  // 3). Evaluate:

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
}
