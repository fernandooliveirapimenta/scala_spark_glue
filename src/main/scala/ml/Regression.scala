package ml

import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.sql.SparkSession

object Regression {
  def main(args: Array[String]): Unit = {

  }

  def main(): Unit ={

    val spark: SparkSession = SparkSession.builder().appName("LinearRegressionExample").getOrCreate()

    val path = "sample_linear_regression_data.txt"

    val training = spark.read.format("libsvm").load(path)
    training.printSchema()

    val lr = new LinearRegression().setMaxIter(100).setRegParam(0.3).setElasticNetParam(0.8)

    val lrModel = lr.fit(training)

    println(s"Coefficients ${lrModel.coefficients} Intercept: ${lrModel.intercept}")

    val trainingSummary = lrModel.summary

    println(s"numIterations: ${trainingSummary.totalIterations}")
    println(s"objectiveHistory: ${trainingSummary.objectiveHistory.toList}")
    trainingSummary.residuals.show()
    println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
    println(s"r2: ${trainingSummary.r2}")
    spark.stop()

  }
}
