package basico.collections

object Exercicios extends App{

  var ex1 = List(1,2,3,4,5)
  println(ex1.contains(3))

  val ex2 = List(6,7) ++ ex1
  println(ex2)
  println(ex2.sum)

  val ex3 = Range(0,16)
  ex3.foreach(e => print(f"$e "))

  println()
  val ex4 = List(2,3,1,4,5,6,6,1,2)
  println(ex4.toSet)

  val ex5 = collection.mutable.Map("Sammy"  -> 3, "Frankie" -> 7, "John" -> 45)
  println(ex5.keys)
  ex5 += ("Mike" -> 27)
  println(ex5.values)



  /**
   * 1.) Can you figure out what method you can use to find out if the list:
   * List(1,2,3,4,5) contains the number 3?
   *
   * 2.) How can you add all the elements of the previous list?
   *
   * 3.) Create an Array of all the odd numbers from 0 to 15
   *
   * 4.) What are the unique elements in the list: List(2,3,1,4,5,6,6,1,2)?
   *
   * 5.) Create a mutable map that maps together Names to Ages.
   * It should have the following key value pairs:
   * Sammy, 3
   * Frankie, 7
   * John, 45
   *
   * Now do the following:
   *
   * 5a) Print out all the keys
   *
   * 5b) Add the key value pair ("Mike",27)
   */




}
