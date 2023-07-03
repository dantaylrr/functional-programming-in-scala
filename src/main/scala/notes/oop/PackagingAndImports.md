# Packaging & Imports.

* A package is a group of definitions under the same name.
* To use a definition by their simple name:
  * Be used in the same package.
  * Import the package from elsewhere.
* It is best practice to mirror the file / project structure being used.
* ```package object```'s hold standalone methods/constants to be used universally inside the package:
  * One per package.
* You can alias imports if two imports share the same name.