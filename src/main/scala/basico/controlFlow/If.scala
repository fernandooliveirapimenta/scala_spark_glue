package basico.controlFlow

object If extends App{

  val num: Int = 5

  if(num > 1)
    println(f"$num Eh maior")

  if(num < 10)
    println(f"$num eh menor")

  if(num == 5)
    println(s" $num eh igual")

  val m: Unit = num match {
    case 0 => println("zero")
    case 5 => println("cinco")
  }

//  m


}

