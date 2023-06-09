# Anonymity.

* We can instantiate types & override fields or methods on the spot when defining a ```val```.

```
trait Animal {
    def eat: Unit
}

val predator = new Animal {
    override def eat: Unit = println("Roar!")
}
```

* If you are extending a class, you need to pass in constructor arguments.
* You need to implement all abstract fields/methods.
* Anonymous classes work for traits & classes whether they are abstract or not.