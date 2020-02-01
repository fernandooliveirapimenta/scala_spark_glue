package basico.controlFlow

import scala.util.control.Breaks._

object While extends App{

  var x = 0

  while (x < 5){
    println(s"x is currently $x")
    x = x + 1
  }


  var y = 0

  breakable(
    while (y < 10){

      println(s"$y")

      y = y+1

      if(y==3) break
    }
  )


  println("foi")
}
