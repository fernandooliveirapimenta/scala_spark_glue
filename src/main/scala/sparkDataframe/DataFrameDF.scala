import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

val spark: SparkSession = SparkSession.builder().master("local").getOrCreate()

val df: DataFrame = spark.read.option("header", "true").option("inferSchema", "true").csv("CitiGroup2006_2008")

//for(row <- df.head(5)){
//  println(row)
//}

df.columns
println()
println()
df.describe().show()
println()
println()
println()
df.select("Volume").show()
println()
println()
df.select("Date","Close").show()
println()
println()

val df2: DataFrame= df.withColumn("HighPlusLow", df("High") + df("Low"))
df2.printSchema()

println()
println()

df2.select(df2("HighPlusLow").as("HPL"), df2("Close")).show()
