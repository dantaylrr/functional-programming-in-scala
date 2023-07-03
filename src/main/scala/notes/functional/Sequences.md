# Sequences.

* Sequences are denoted by `Seq`.
* Sequences are a general interface for data structures that:
  * Have a well-defined order.
  * Can be indexed.
* Supports various operations such as:
  * `apply`, `iterator`, `length`, `reverse` for indexing & iterating.
  * Concatenation, appending & prepending for new sequence construction.
  * Grouping, sorting, searching, zipping etc...

# Lists.

```
sealed abstract class List[+A]
case object Nil extends List[Nothing]
case class ::[A](val hd: A, val tl: List[A]) extends List[A]
```

* A LinearSeq immutable linked list:
  * head, tail, isEmpty methods are fast O(1).
  * Most other operations are O(n), such as length & reverse.
* Sealed class & has two sub-types:
  * object Nil (which is empty).
  * class ::.

# Arrays.

```
final class Array[T] extends java.io.Serializable with java.lang.Cloneable
```

* The equivalent of Java arrays.
  * Can be manually constructed with pre-defined lengths.
  * Are mutable.
  * Are interoperable with Java's T[] arrays.
  * Indexing is fast.
* Where is the Seq???

# Vectors.

```
final class Vector[+A]
```
* Is the default implementation for _immutable sequences_.
  * Effectively constant indexed read & write - O(log_32(n)).
  * Fast element addition with append/prepend.
  * Implemented as a fixed-branched trie (branch factor 32).
  * Good performance for large sizes.