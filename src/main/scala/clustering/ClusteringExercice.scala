
object ClusteringExercice {

  def main(): Unit = {

    import org.apache.log4j.Level._
    import org.apache.spark.sql.SparkSession
    import org.apache.log4j._
    import org.apache.spark.ml.clustering.KMeans
    Logger.getLogger("org").setLevel(ERROR)

    val spark = SparkSession.builder().getOrCreate()
    import spark.implicits._

    val dataSet = spark.read.option("header", "true")
      .option("inferSchema", "true")
      .csv("Wholesale customers data.csv")


    val featureData = dataSet.select(
      $"Fresh",
      $"Milk",
      $"Grocery",
      $"Frozen",
      $"Fresh",
      $"Detergents_Paper",
      $"Delicassen"
    )

    import org.apache.spark.ml.feature.VectorAssembler
    import org.apache.spark.ml.linalg.Vectors


    val assembler = new VectorAssembler().setInputCols(Array(
      "Fresh",
      "Milk",
      "Grocery",
      "Frozen",
      "Fresh",
      "Detergents_Paper",
      "Delicassen"
    ))
      .setOutputCol("features")

    val trainingData = assembler.transform(featureData)
      .select("features")

    val kMeans = new KMeans().setK(4)

    val model = kMeans.fit(trainingData)

    val WSSSE = model.computeCost(trainingData)

    println(s"The Within Set Sum of Squared Erros was: $WSSSE")

  }

}
