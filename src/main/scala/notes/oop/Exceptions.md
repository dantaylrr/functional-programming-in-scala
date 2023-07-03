# Exceptions.

* Exceptions & Errors crash programs.
* We can throw exceptions if we know of a particular potential pit-fall in our program.
* We can then attempt to catch these errors should they occur, whilst still executing parts of our code.
* Throwing an exception returns nothing.
* You can catch exceptions using 
```
try {
} catch {
 case ...
} finally
```
* You can define custom exceptions by extending the built-in ```Exception``` class.