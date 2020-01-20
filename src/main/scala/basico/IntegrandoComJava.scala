package basico

import java.util.{Date, Locale}
import java.text.DateFormat._

object IntegrandoComJava {

  def main(args: Array[String]){
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }

}
