import org.apache.spark.sql.{DataFrame, SparkSession}

object ExercicioNetflix extends App {

  val spark: SparkSession = SparkSession.builder().getOrCreate()
  val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("Netflix_2011_2016.csv")


  df.printSchema()

  //Column names: Date, Open, High, Low, Close, Volume, Adj


  //first 5 columns

  df.select("Date", "Low").limit(5).show()


  // describe

  df.describe()



  //Create a new df with a columns called HV Ratio

  df.groupBy("Date").mean("High", "Volume").show()
  //val dfMaxHigh = df.select(max("High"))

  // Day had peak high in price

  df.groupBy("Date").max("High")


  import org.apache.spark.sql.functions._

  df.select(max("Volume")).show()
  df.select(min("Volume")).show()

  //
  df.filter("Volume < 120460210").count()

  df.filter("High > 500")
}
