package lectures.oop.basics

object AnswerAuthorNovel extends App {

  // Testing our Author class
  val author1 = new Author(firstName = "Stephen", surname = "King", yearOfBirth = 1947)
  println(s"Here is the author's full name: ${author1.fullName()}")

  // Testing our Novel class
  val it = new Novel(name = "IT", releaseYear = 1986, author = author1)
  it.ageOfAuthor()
  it.isWrittenBy()
  val copiedIt = it.copy(newYearOfRelease = 2017)
  println(copiedIt.releaseYear) // Should now be 2017

}

class Author(firstName: String, surname: String, val yearOfBirth: Int) {

  def fullName(firstName: String = this.firstName, surname: String = this.surname): String = {
    // We need to return a val here so that it can be used in "isWrittenBy" in the Novel class
    val fullName = s"${firstName} ${surname}."
    fullName
  }
}

class Novel(name: String, val releaseYear: Int, author: Author) {

  def ageOfAuthor(releaseYear: Int = this.releaseYear, authorAge: Int = author.yearOfBirth): Unit = {
    println(s"The author was ${releaseYear - authorAge} years old when they wrote this book.")
  }

  def isWrittenBy(author: Author = this.author): Unit = {
    println(s"This novel was written by ${author.fullName()}")
  }

  def copy(newYearOfRelease: Int): Novel = {
    new Novel(name = this.name, releaseYear = newYearOfRelease, author = this.author)
  }
}
