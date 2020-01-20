package basico

object FuncaoAnonima {

  def oncePerSecond(callBack: () => Unit): Unit = {
    while (true) {
      callBack();
      Thread.sleep(1000)
    }
  }

  def main(args: Array[String]): Unit ={
    oncePerSecond(() => oncePerSecond( () => oncePerSecond( () => println("oi"))))
  }

}
