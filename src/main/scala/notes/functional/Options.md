# Options.

* An option is a wrapper for a value that might be present or not.
```
sealed abstract class Option[+A]
case class Some[+A](x: A) extends Option[A]
case object None extends Option[Nothing]
```
* Options are present in many places, such as Maps, where we issue a .get() method that will return None if the key doesn't exist.