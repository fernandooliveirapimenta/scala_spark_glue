import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.clustering.{KMeans, KMeansModel}
import org.apache.spark.ml.evaluation.ClusteringEvaluator

object KMeans {

  def main(): Unit = {

    val spark: SparkSession = SparkSession.builder().getOrCreate()

    val dataSet: DataFrame = spark.read.format("libsvm").load("sample_kmeans_data.txt")

    val kmeans: KMeans = new KMeans().setK(2).setSeed(1L)
    val model: KMeansModel = kmeans.fit(dataSet)

//    val cE: ClusteringEvaluator = new ClusteringEvaluator()
    val WSSSE: Double = model.computeCost(dataSet)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)

//    val kMeans: KMeans = new KMeans().setK(2).setSeed(1L)
//    val model = kMeans.fit(dataSet)

//    val cE: ClusteringEvaluator = new ClusteringEvaluator()
//    val WSSSE: Double = cE.evaluate(dataSet)

//    println(s"Within Set Sum of Squared Errors = $WSSSE")

//    println("Cluster Centers:")
//    model.clusterCenters.foreach(println)


  }

}
