import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object DataFrameOperations extends App {

  val spark: SparkSession = SparkSession.builder().getOrCreate()

  val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("CitiGroup2006_2008")

  df.printSchema()

  ////////////////
  ////////////////

  import  spark.implicits._

  df.filter($"Close">492).orderBy($"Close").show()


  println()
  println()

  df.filter("Open = 496.9").show()

  println()
  println()

  df.filter($"Close" < 480 && $"High" < 480).show()

  println()
  df.filter("Close < 480 AND High < 480").show()

  println()
  println()

  val CH_low: Array[Row] = df.filter($"Close" < 480 && $"High" < 480).collect()

  val ChLowCount: Long = df.filter("Close < 480 AND High < 480").count()

  println()

  df.filter("High = 484.40").show()

  println()
  println()
  df.filter($"High" === 484.40).show()

  import org.apache.spark.sql.functions._


  df.select(corr("High","Low")).show()

}
