# Basics.

* We want to work primarily with functions:
  * We want to be able to pass functions as parameters.
  * We want to be able to use functions as values.
* Issue is Scala works on top of the JVM.
  * Designed for Java.
  * First-class elements are objects (instances of classes).
* The solution is that all Scala functions are objects.
  * Function traits up to 22 parameters.
  * Syntactic sugar types.
```
trait Function1[-A, B] {
    def override apply(element: A): B
}

==

Function2[Int, String, Int]

==

(Int, String) => Int
```