package lectures.functional.HandlingFailure

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // Exceptions are handled in try-catch-finally expressions

  // There is a Try wrapper for a computation that might fail or not

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you!")

  // Try objects
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // Syntactic sugar
  val anotherPotentialFailure = Try {
    // code that might throw an exception
  }

  // Utils
  println(potentialFailure.isSuccess) // Return a bool

  // orElse
  def backupMethod(): String = "A valid result."
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the API, wrap your computation in a try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result.")
  val betterFallbackTry = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallbackTry)

  // map, flatmap & filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for comprehensions

  val host = "localhost"
  val port = "8080"
  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted.")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port.")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // If you get the HTML page from the connection, print it to the console. i.e. call renderHTML

  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  // short-hand version
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for comprehension
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)
}
