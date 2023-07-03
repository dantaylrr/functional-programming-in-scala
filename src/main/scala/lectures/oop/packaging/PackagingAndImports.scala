package lectures.oop.packaging // Every definition within this directory

import lectures.oop.basics.{Author, Novel}

import java.util.{Date => Utl}
import java.sql.{Date => Sql}

object PackagingAndImports extends App {

  // You can use package members defined in other files directly in this or any files, providing they are under the "package" directory outlined at the start of the file

  // Package members are accessible by their simple name - let's use our Author class from OO basics

  val newAuthor = Author("Daniel", "Taylor", 1997) // This works just fine

  // You can also refer to them directly using the full path name - called the fully qualified name

  val newAuthor2 = lectures.oop.basics.Author("Daniel", "Taylor", 1997)

  // Packages are defined in a hierarchy matching the folder structure of our project... IDE's like IntelliJ do this by default for us

  /*

  What if we want to use universal constants or methods that we don't want to live inside a class?

  We can use "Package Objects"

  */

  // We can call our Package Objects directly - they are pretty rarely used in practice
  sayHello

  // We can also import more than 1 package/class/object at the same time using {} on the imports on line 3

  val newNovel = new Novel("A Book", 2009, newAuthor)

  // We can also import everything under a file using "_"... lectures.oop.basics._

  // You can also assign Alias's to imports using => ... See above for "Date" imports

  /*

  Default imports:

  java.lang - String, Object, Exception
  scala - Int, Nothing, Function
  scala.Predef - println(), ???

  */

}
