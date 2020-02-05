import org.apache.log4j.Level.ERROR
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.regression.LinearRegression
import org.apache.log4j._

object ExercicioLr {

  def start(): Unit ={

    Logger.getLogger("org").setLevel(ERROR)


    val spark = SparkSession.builder().getOrCreate()

    import spark.implicits._
    val data = spark.read
      .option("header","true")
      .option("inferSchema","true")
      .format("csv")
      .load("Ecommerce Customers")

    data.printSchema()


    val colnames = data.columns
    val firstRow = data.head(1)(0)
    println(firstRow)
    println("\n")
    println("Example Data Row")

    for(ind <- Range(1, colnames.length)){
      println(colnames(ind))
      println(firstRow(ind))
      println("\n")
    }

    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vectors

    val df = data
      .select(
        data("Yearly Amount Spent").as("label"),
        $"Avg Session Length",
        $"Time on App",
        $"Time on Website",
        $"Length of Membership"
      )

    val assembler = new VectorAssembler()
      .setInputCols(
        Array(
          "Avg Session Length",
          "Time on App",
          "Time on Website",
          "Length of Membership"
        )
      )
      .setOutputCol("features")

    val output = assembler
      .transform(df)
      .select($"label",$"features")

    val lr = new LinearRegression()

    val lrModel = lr.fit(output)

    println(s"Coeff: ${lrModel.coefficients}," +
      s" intercept: ${lrModel.intercept}")


    val trainingSummary = lrModel.summary

    trainingSummary.residuals.show()

    println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
    println(s"MSE: ${trainingSummary.meanSquaredError}")
    println(s"R2: ${trainingSummary.r2}")


  }

}
