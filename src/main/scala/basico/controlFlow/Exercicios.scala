package basico.controlFlow

trait M{
  def m(i: Int):Boolean
}

class MUtil{
  def check(i: Int): Boolean ={
    i > 5
  }

  def checkList(n: Int, l: List[Int]): Boolean={
    l.contains(n)
  }

  def calcList(l: List[Int]): List[Int]={

    def logic(a: Int): Int = {
      if(a == 7)
        return a * 2
       a
    }


    l.map(logic)

  }
}


object T extends App{


  val l = List(1,2,3,7)

  val t =l.map(a => {
    a * 2
  })

  println(t)

  def logic(a: Int): Int = {
    if(a == 7)
      return a * 2
    a
  }


 println(l.map(logic).sum)

}

object Exercicios extends App{

  // ex1

  def checkEven(num: Int): Boolean = {
    num % 2 == 0
  }

  def checkEvenOneLine(num: Int): Boolean
  = num % 2 == 0

  println(checkEven(4))
  println(checkEven(3))
  println(checkEvenOneLine(6))



  // ex2


  def checkList(nums: List[Int]): Boolean = {
    for(n <- nums){
      if(n%2==0)
        return true
    }
    false
  }

  val evenSample = List(1,2,3,4,5)
  val oddSample = List(1,3,5,7)
  println(checkList(evenSample))


  //ex3


  def lucky(nums: List[Int]): Int = {
    var output = 0
    for(n <- nums) {
      if(n == 7)
        output = output + 14
      else
        output = output + n
    }
    output
  }


  val numbers = List(1,2,3,7)
  println(lucky(numbers))


  //ex4

  def balanceCheck(l: List[Int]): Boolean ={
    var fi = 0
    var se = 0

    se = l.sum

    for(i <- l.indices){
      fi = fi + l(i)
      se = se - l(i)

      if(fi == se)
        return true
    }

    false

  }


  val ballList = List(1,2,3,4,10)
  val ballList2 = List(2,3,3,2)
  val unbalList = List(10,20,70)

  println()
  println()
  println(balanceCheck(ballList))
  println(balanceCheck(ballList2))
  println(balanceCheck(unbalList))


  //ex5
  println()
  println()
  println()

  def palindromeCheck(st: String): Boolean ={
   st == st.reverse
  }

  println(palindromeCheck("abccba"))
  println(palindromeCheck("hello"))







}
