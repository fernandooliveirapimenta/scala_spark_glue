package basico.controlFlow

object For extends App{

  for(item <- List(1,2,3)){
    println(item)
  }

  for(num <- Array.range(0,4)){
    print(num)

    if(num%2 == 0)
      println(s"$num is even")


  }

  val names = List("John", "Abe", "Cindy")

  for(name <- names){
    if(name.startsWith("J"))
      println(name)
  }

  println(soma(5))
  println(soma(5,3))
  println(soma(5,n2=4))


  def soma(n: Int, n2: Int = 2): Int ={
     n + n2
  }

}
