# Generics.

* Allows us to use the same code on many (potentially unrelated) types.
* Done by placing an "anonymous" type in square brackets after a class or method.
* You can also have multiple type parameters (commonly used with the Map data-type), ```Map[Key, Value]```.
* We've also learnt about Variance:
  * If B extends A, should ```List[B]``` extend ```List[A]```?
    * Yes - Covariance ```[+A]```.
    * No - Invariance (default) ```[A]```.
    * Absolutely not - Contravariance ```[-A]```.
* We've learnt about bounded types, defined by ```[... <: ...]``` or ```[... >: ...```.