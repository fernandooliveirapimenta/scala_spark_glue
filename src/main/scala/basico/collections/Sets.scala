package basico.collections

object Sets extends App{



  val s = Set(1,2,3)
  println(s)



  val sm = collection.mutable.Set(1,2,3)

  sm += 4

  sm.foreach(f => println(f))

  val imutis = collection.mutable.Set(1,2,3)

  imutis.add(5)

  println(imutis)

  println(imutis.min)
  println(imutis.max)

}
