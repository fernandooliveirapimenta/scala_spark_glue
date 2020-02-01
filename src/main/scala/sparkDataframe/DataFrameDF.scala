package sparkDataframe


object DataFrameDF extends App{

  import org.apache.spark.sql.SparkSession

  val spark = SparkSession
    .builder()
    .master("local")
    .getOrCreate()

//  val df = spark.read.csv("")

}
