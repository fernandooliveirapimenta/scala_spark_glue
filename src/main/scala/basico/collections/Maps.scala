package basico.collections

object Maps extends App{

  val mymap = Map(("a", 1), ("b", 2), ("c", 3))

  println(mymap)


  println(mymap("a"))
  println(mymap("b"))
//  println(mymap("naoexiste")) lança exeption

  //nao lanca
  println(mymap get "naoexiste")


  val mymutmap = collection.mutable.Map(("x", 1), ("y", 2), ("z", 3))
  println(mymutmap)
  mymutmap.put("nova", 5)
  println(mymutmap)
  mymutmap += ("fernando"->5)
  println(mymutmap)

  println(mymutmap.keys)
  println()
}

object Maps2 extends App{
  val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF","peru" -> "#CD853F")

  val nums: Map[Int, Int] = Map()

  println(colors.keys)
  println(colors.values)
  println(colors.head)
  println(colors.tail)
  println(colors.isEmpty)
  println(nums.isEmpty)
}

object MapsConcatenacao extends App{
  val colors1 = Map("red" -> "#FF0000", "azure" -> "#F0FFFF", "peru" -> "#CD853F")
  val colors2 = Map("blue" -> "#0033FF", "yellow" -> "#FFFF00", "red" -> "#FF0000")

  val addAll = colors1 ++ colors2
  println(f"add all $addAll")

  val addAll2 = colors1.++(colors2)
  println(f"add all $addAll2")

  val f = F
  f.++-()

}


object F {
  def ++-(): Unit = {
    println("metodo")
  }
}
