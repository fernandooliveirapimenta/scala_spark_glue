import org.apache.spark.sql.{DataFrame, SparkSession}

val spark: SparkSession = SparkSession.builder().getOrCreate()

val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("")