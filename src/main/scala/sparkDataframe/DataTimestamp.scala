import org.apache.spark.sql.{DataFrame, SparkSession}

val spark: SparkSession = SparkSession.builder().getOrCreate()

val df: DataFrame = spark.read.option("header", "true").option("inferSchema","true").csv("CitiGroup2006_2008")


df.printSchema()

df.select(month(df("Date"))).show()
df.select(year(df("Date"))).show()

println()

val df2 = df.withColumn("Year", year(df("Date")))
val dfavgs = df2.groupBy("Year").mean()
dfavgs.select($"Year",$"avg(Close)").show()

val dfmin = df2.groupBy("Year").min()
dfmin.select($"Year",$"min(Close)").show()
