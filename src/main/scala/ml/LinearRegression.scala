package ml

import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.log4j._

object LinearRegression {

  def main(args: Array[String]): Unit = {

  }

  def start(): Unit ={
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().getOrCreate()

    val data = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("USA_Housing.csv")

    data.printSchema()



  }

}
