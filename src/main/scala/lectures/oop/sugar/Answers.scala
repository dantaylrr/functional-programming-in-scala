package lectures.oop.sugar

object Answers extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int = 0) {

    def +(person: Person): String = {
      s"${this.name} is hanging out with ${person.name}."
    }

    // 1). Overload the + method
    def +(nickName: String): String = {
      s"${name} has a nick name of ${nickName}."
    }

    // 2). Overload the unary_+ operator
    def unary_+ : Person = {
      new Person(name, favouriteMovie, age + 1)
    }

    // 3). learns & learnsScala
    def learns(language: String = "scala"): String = {
      s"${name} learns ${language}."
    }

    def learnsScala(): String = {
      learns()
    }

    def apply(): String = {
      s"Hello, my name is $name, & I like $favouriteMovie."
    }

    // 4). Overload the apply() method
    def apply(n: Int): String = {
      s"${name} watched ${favouriteMovie} ${n} times."
    }
  }

  val dan = new Person("Dan", "Good Will Hunting")
  // Verify overloaded "+" method with nickname "dt"
  println(dan + "dt")

  // Verify unary_+ method
  println((+dan).age)

  // Verify the learns & learnsScala methods by calling the parent
  println(dan.learnsScala())

  // Verify overloaded apply method
  println(dan(4))
}
