import org.apache.spark.sql.{DataFrame, SparkSession}

object MissingData extends App {

  val spark: SparkSession = SparkSession.builder().getOrCreate()

  val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("ContainsNull.csv")
  df.printSchema()


  df.show()


  println()
  df.na.drop().show()
  df.show()

  df.na.drop(2).show()

  df.na.fill(100).show()
  df.na.fill("Missing Name").show()

  df.na.fill("Missing Name", Array("Name")).show()
  println("completo")
  val df2: DataFrame = df.na.fill(200, Array("Sales"))
  df2.na.fill("missing name", Array("Name")).show()




}

