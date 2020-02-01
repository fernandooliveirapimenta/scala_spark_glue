package basico.controlFlow

object Functions extends App{


  def simple(): Unit = {
    println("simple print")
  }


  simple()


  def adder(num1: Int, num2: Int): Int ={
    num1 + num2
  }


  println(adder(5,3))

  def greetName(name: String): String={
     s"Hello $name"
  }

  val fullGreet = greetName("Jose")
  println(fullGreet)


  def isPrime(numCheck:Int): Boolean = {
    for(n <- Range(2, numCheck)){
      if(numCheck%n == 0){
        return false
      }
    }
    true
  }

  println(isPrime(10))
  println(isPrime(23))


  val nums = List(1,2,3,7)

  def check(nums:List[Int]): List[Int]={
    nums
  }

  println(check(nums))


}
