package basico

object FuncoesSaoObjetos {

  def oncePerSecond(c: () => Unit){
    var fim = 0
    while (fim <= 10) { c(); Thread sleep 1000; fim = fim + 1}
  }

  def timeFlies(): Unit ={
    println("o tempo corre como um raio ...")
  }

  def main(args: Array[String]): Unit ={
    oncePerSecond(timeFlies)
  }

}
