# HOFs & Curries.

* You can pass functions as parameters.
* Can also return functions as a result.
* This introduces the concept of higher-order functions.
  * These are functions that take one or more functions as parameters.
  * Or, returns a function as a result.
* Currying involves functions with multiple parameter lists.
```
def curriedFormatter(c: String)(a: Double): String = ...
```