import org.apache.spark.sql.{DataFrame, SparkSession}

object ExerciciosResolvidos {
  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder().appName("ExerciciosResolvidos").getOrCreate()
    val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("Netflix_2011_2016.csv")


    df.printSchema()


    df.columns

    df.head(5)

    df.describe().show()

    //Create a new dataframe with a column called HV Ration that is the ratio
    //of th High Price versus volume of stock traded for a day

    val df2 = df.withColumn("HV Ratio", df("High")/df("Volume"))

    df2.show(5)

    import org.apache.spark.sql.functions._

    //What day the peak High in Price
    df.orderBy(desc("High")).show(1)

    //What is the mean of the Close column?
    df.select(mean("Close")).show()

    //What is the max and min of the Volume colums
    df.select(max("Volume"), min("Volume")).show()

    //For Scala/Spark $ Syntax?
    import spark.implicits._

    //How many days was the Close Lower than $600?
    df.filter($"Close"<600).count()
    df.filter("Close < 600").count()

    //What percentage of the time was the High greater than $500
    (df.filter($"High">500).count() * 1.0 / df.count()) * 100

    //What is the Pearson correlation between High and Volume?
    df.select(corr("High", "Volume")).show()

    //What is the max High per year?

    val yearDf = df.withColumn("Year", year(df("Date")))
    val yearMax = yearDf.select($"Year", $"High").groupBy("Year").max()
    yearMax.show()

    yearMax.select($"Year", $"max(High)").show()


    //What is the average Close for each Calender Month?
    val monthDf = df.withColumn("Month", month(df("Date")))
    val monthAvgs = monthDf.select($"Month", $"Close").groupBy("Month").mean("Close")
    monthAvgs.show()

    monthAvgs.select($"Month", $"avg(Close)").orderBy("Month").show()
  }
}
