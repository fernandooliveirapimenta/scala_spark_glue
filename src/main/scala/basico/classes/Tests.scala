package basico.classes

object Tests {

  def main(args: Array[String]): Unit ={

    val c = new ComParametros(imaginary = 3.3, real = 2);
    val f = new ComParametros(3.2, 2.3)

    println(c.im())
    println(f.re())

  }

}
