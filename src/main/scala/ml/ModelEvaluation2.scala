
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}
import org.apache.log4j._
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

Logger.getLogger("org").setLevel(Level.ERROR)

object ModelEvaluation2{

  def main(): Unit = {

    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._

    val data = spark.read.option("header", "true")
      .option("inferSchema", "true")
      .format("csv")
      .load("USA_Housing.csv")

    data.printSchema()

    val df = data.select(data("Price").as("label"),
    $"Avg Area Income",
    $"Avg Area House Age",
    $"Avg Area Number of Rooms",
    $"Avg Area Number of Bedrooms",
    $"Area Population",
    $"Price",
    $"Address"
    )

    val assembler = new VectorAssembler()
      .setInputCols(Array(
        "Avg Area Income",
        "Avg Area House Age",
        "Avg Area Number of Rooms",
        "Avg Area Number of Bedrooms",
        "Area Population",
        "Price",
        "Address"
      ))

    val output = assembler.transform(df).select($"label",$"features")

    val Array(training, test) = output.select("label","features")
      .randomSplit(Array(0.7,0.3))

  }

}
