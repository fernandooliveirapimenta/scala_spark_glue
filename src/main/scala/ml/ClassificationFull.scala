import org.apache.log4j.Level.ERROR
import org.apache.log4j.Logger
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.OneHotEncoderEstimator
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler, VectorIndexer}
import org.apache.spark.ml.linalg.Vectors

object ClassificationFull {

  def main(): Unit ={
    Logger.getLogger("org").setLevel(ERROR)

    val spark = SparkSession.builder()
      .getOrCreate()
    import spark.implicits._

    val data = spark.read.option("header","true")
      .option("inferSchema","true")
      .format("csv")
      .load("titanic.csv")

    data.printSchema()

    val columns: Array[String] = data.columns
    val firstRow = data.head(1)(0)
    println(firstRow)
    println(firstRow(0))
    println("\n")
    println("exemple Data Row")
    println("\n")

    for(colNameInx <- columns.indices){
      println(columns(colNameInx))
      println(firstRow(colNameInx))
      println("\n")
    }

    for(col <- data.columns){
      print(s" $col ")
    }
    println("\n")

    val logRegDataAll = data.select(data("Survived").as("label"),
      $"Pclass",
      $"Name",
      $"Sex",
      $"Age",
      $"SibSp",
      $"Parch",
      $"Fare",
      $"Embarked"
    )

    val logRegData = logRegDataAll.na.drop()



    val genderIndexer = new StringIndexer()
      .setInputCol("Sex")
      .setOutputCol("SexIndex")
    val embarkIndexer = new StringIndexer()
      .setInputCol("Embarked")
      .setOutputCol("EmbarkedIndex")

    val genderEncoder = new OneHotEncoderEstimator()
      .setInputCols(Array("SexIndex"))
      .setOutputCols(Array("SexVec"))

    val embarkEncoder = new OneHotEncoderEstimator()
      .setInputCols(Array("EmbarkedIndex"))
      .setOutputCols(Array("EmbarkVec"))

    val assembler = new VectorAssembler()
      .setInputCols(Array("Pclass", "SexVec", "Age", "SibSp",
        "Parch", "Fare", "EmbarkVec"))
      .setOutputCol("features")


    val Array(training, test) = logRegData.randomSplit(Array(0.7,0.3), seed=12345)

    val lr = new LogisticRegression()

    val pipeline = new Pipeline()
      .setStages(Array(genderIndexer,embarkIndexer
        ,genderEncoder,embarkEncoder,assembler,lr))


    val model = pipeline.fit(training)

    val results = model.transform(test)


    import org.apache.spark.mllib.evaluation.MulticlassMetrics

    val predictionAndLabels = results.select($"prediction",$"label").as[(Double,Double)].rdd

    val metrics = new MulticlassMetrics(predictionAndLabels)
    println(metrics.confusionMatrix)


  }

}
