import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

val spark: SparkSession = SparkSession.builder().getOrCreate()

val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("Sales.csv")

df.printSchema()

println()
println()

df.select("*").show()

println()
println()

df.groupBy("Company").mean().show()
df.groupBy("Company").avg().show()
df.groupBy("Company").count().show()
df.groupBy("Company").sum().show()
df.groupBy("Company").max().show()
df.groupBy("Company").min().show()
println()

df.select(sum("Sales")).show()

df.select(countDistinct("Sales")).show()
df.select(sumDistinct("Sales")).show()
df.select(variance("Sales")).show()
df.select(stddev("Sales")).show()
df.select(collect_set("Sales")).show()

println()
df.show()
println()
df.orderBy("Sales").show()

