package basico.collections

object Arrays extends App{


  // imutable diferente da list

  val arr = Array(1,2,3)

  val s = arr.drop(2)

  s.foreach(f => println(f))

  println(arr.toString)


  val range = Array.range(0,10)



}
