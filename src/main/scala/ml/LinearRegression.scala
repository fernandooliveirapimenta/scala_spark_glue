import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.log4j._
import org.apache.spark.sql.types.DoubleType

object LinearRegression {

  def main(args: Array[String]): Unit = {

  }

  def start(): Unit ={
    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._
    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vectors
    import org.apache.spark.sql.functions._

    var data = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("USA_Housing.csv")
    data.printSchema()

    val cols = Array("AvgAreaIncome","AvgAreaHouseAge","AvgAreaNumberofRooms","AreaPopulation")

    for(colName <- cols){
      data = data.withColumn(colName, col(colName).cast("double"))
    }

    data.printSchema()

    val df = data.select(data("Price").as("label"), $"AvgAreaIncome", $"AvgAreaHouseAge", $"AvgAreaNumberofRooms", $"AreaPopulation")

    df.printSchema()

    val assembler = new VectorAssembler().setInputCols(cols).setOutputCol("features")

    val output = assembler.setHandleInvalid("skip").transform(df).select($"label",$"features")
    output.printSchema()

    val lr = new LinearRegression()

    val lrModel = lr.fit(output)

    println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    val trainingSummary = lrModel.summary

    println(s"numIterations: ${trainingSummary.totalIterations}")
    println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")

    trainingSummary.residuals.show()

    println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
    println(s"MSE: ${trainingSummary.meanSquaredError}")
    println(s"r2: ${trainingSummary.r2}")

  }

}
