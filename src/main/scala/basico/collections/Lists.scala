package basico.collections

object Lists extends App{

  val evens = List(2,4,6,9,10)
  println(evens)

  println(evens.head)

  val listInt = List(List(1,3), List(2,3))
  println(listInt)

  val listS = List(("a",1),("b", 2),("c", 3))
  println(listS)

  val x = List(1,2,3,4,5,6,7,8)

  val l = x slice(0,3)
  println(l)
}
