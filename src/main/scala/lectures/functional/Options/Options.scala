package lectures.functional.Options

import scala.util.Random

object Options extends App {

  // Implemented to combat NullPointer exceptions - or just null's in general
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // Unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) // <- this is wrong! Some(null)...
  val result = Option(unsafeMethod()) // The companion object of Option will determine whether to return Some or None... in this case None

  // Chained methods
  def backupMethod(): String = "A Valid Result!"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod())) // A nice fall-back

  // Designing unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A Valid Result!")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on Options
  println(myFirstOption.isEmpty) // Returns False
  println(myFirstOption.get) // This is unsafe - do not use it

  // map, flatMap, filter
  println(myFirstOption.map(x => x * 2)) // Some(8)
  println(myFirstOption.filter(x => x % 2 != 0)) // None
  println(myFirstOption.flatMap(x => Option(x * 10))) // Some(40)

  // For-comprehensions

  /*

  Given an API with the following:

  */

  val config: Map[String, String] = Map(
    // Fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected!"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // If you can establish a connection, print the connect method
  val host = config.get("host")
  val port = config.get("port")

  /*
  if (h != null)
    if (p != null)
      Connection.apply(h, p)

  return null
  */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  /*
  if (c != null)
    c.connect

  return null
  */
  val connectionStatus = connection.map(c => c.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  // Chained solution
  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection.apply(h, p))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val connectionStatus2 = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield {
    connection.connect
  }
  connectionStatus2.foreach(println)
}
