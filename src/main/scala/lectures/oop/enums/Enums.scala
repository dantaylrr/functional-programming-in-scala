package lectures.oop.enums

import lectures.oop.enums.Enums.PermissionsWithBits

object Enums {

  // Enums are a data type that can be defined similarly to a class

  // Creates a sealed data type of Permissions, with the only possible instances being READ/WRITE/EXECUTE/NONE
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // We can add fields and methods
    def openDucmument(): Unit = {
      if (this == READ) println("Opening document...")
      else println("Permission denied...")
    }
  }

  val somePermissions: Permissions = Permissions.READ

  // Enums can also take constructor arguments - although feels slightly boiler-plate
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = { // Some logic here...
      PermissionsWithBits.NONE
    }
  }

  // The standard API with Enums

  val somePermissionsOrdinal = somePermissions.ordinal // Gives the index at which this enum is defined

  // We can also list or extract all possible Enum cases - useful if we're dealing with an Enum from an external library

  val allPermissions = PermissionsWithBits.values

  // We can instantiate an Enum from a string input
  val readPermission: Permissions = Permissions.valueOf("READ")

  // We can then call this in our main def or any def if we wanted
  def main(args: Array[String]): Unit = {
    somePermissions.openDucmument()
    println(somePermissions.ordinal)
  }

}
