import org.apache.spark.ml.PipelineModel
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset}

object ExercicioClassification {

  def main(): Unit ={

    import org.apache.spark.ml.classification.LogisticRegression
    import org.apache.spark.sql.SparkSession
    import org.apache.spark.sql.functions._
    import org.apache.log4j._

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark: SparkSession = SparkSession.builder().getOrCreate()

    import spark.implicits._

    val data: DataFrame = spark.read.option("header","true")
      .option("inferSchema","true")
      .format("csv")
      .load("advertising.csv")

    data.printSchema()

    val timeData: DataFrame = data.withColumn("Hour",
      hour(data("Timestamp")))

    val logRegData: DataFrame = timeData.select(
      data("Clicked on Ad").as("label"),
      $"Daily Time Spent on Site",
      $"Age",
      $"Area Income",
      $"Daily Internet Usage",
      $"Hour",
      $"Male"
    )

    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vectors

    val assemble: VectorAssembler = new VectorAssembler()
      .setInputCols(
        Array(
          "Daily Time Spent on Site",
          "Age",
          "Area Income",
          "Daily Internet Usage",
          "Hour",
          "Male"
        )
    ).setOutputCol("features")


    val Array(training, test) = logRegData
      .randomSplit(Array(0.7,0.3), seed = 12345)


    import org.apache.spark.ml.Pipeline

    val lr: LogisticRegression = new LogisticRegression()

    val pipeline: Pipeline = new Pipeline()
      .setStages(Array(assemble, lr))

    val model: PipelineModel = pipeline.fit(training)

    val results: DataFrame = model.transform(test)

    import org.apache.spark.mllib.evaluation.MulticlassMetrics

    val predictionAndLabels: RDD[(Double, Double)] = results
      .select($"prediction", $"label")
      .as[(Double,Double)].rdd

    val metrics = new MulticlassMetrics(predictionAndLabels)

    println("Confusion Matrix")

    println(metrics.confusionMatrix)











  }

}
