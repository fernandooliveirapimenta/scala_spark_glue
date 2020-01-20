object Fernando extends App {

  val names = List("oi", "ola")
  println(names);

  val nUpper = names map(_ toUpperCase())
  val nUpper2 = names.map(_.toUpperCase())

  println(nUpper)
  println(nUpper2)

  val n = new Nome
  val f = n.falar()
 print(f)

}

class Nome{

  def falar(): String = {
   "oi"
  }
}
