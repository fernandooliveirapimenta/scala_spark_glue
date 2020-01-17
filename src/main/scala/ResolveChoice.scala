import com.amazonaws.services.glue.util.JsonOptions
import com.amazonaws.services.glue.{DynamicFrame, GlueContext}
import org.apache.spark.SparkContext

object ResolveChoice {
  def main(args: Array[String]): Unit = {
    val sc: SparkContext = new SparkContext()
    val glueContext: GlueContext = new GlueContext(sc);

    val spark = glueContext.getSparkSession

    val dbName = "medicare"
    val tblName = "medicare"

    val baseOutputDir = "s3:///glue-sample-target/output-dir"
    val medicareCast = s"$baseOutputDir/medicare_json_cast"
    val medicareProject = s"$baseOutputDir/medicare_json_project"
    val medicareCols = s"$baseOutputDir/medicare_json_make_cols"
    val medicareStruct = s"$baseOutputDir/medicare_json_make_struct"
    val medicareSql = s"$baseOutputDir/medicare_json_sql"

    val medicareDyf = glueContext.getCatalogSource(database = dbName, tableName = tblName).getDynamicFrame()

    val medicareRestCast = medicareDyf.resolveChoice(specs = Seq(("provider id", "cast:long")))
    val medicareRestProject = medicareDyf.resolveChoice(specs = Seq(("provider id", "project:long")))
    val medicareResMakeCols = medicareDyf.resolveChoice(specs = Seq(("provider id", "make_struct")))
    val medicareResMakeStruct = medicareDyf.resolveChoice(specs = Seq(("provider id", "make_struct")))

    val medicareDf = medicareDyf.toDF()
    medicareDf.createOrReplaceGlobalTempView("medicareTable")
    val medicareSqlDf = spark.sql("SELECT * FROM medicareTable WHERE `total discharges` > 30")
    val medicareSqlDyf = DynamicFrame(medicareSqlDf, glueContext).withName("medicare_sql_dyf")

    glueContext.getSinkWithFormat(connectionType = "s3",
      options = JsonOptions(Map("path" -> medicareCast)), format = "json").writeDynamicFrame(medicareRestCast)

    glueContext.getSinkWithFormat(connectionType = "s3",
      options = JsonOptions(Map("path" -> medicareProject)), format = "json").writeDynamicFrame(medicareRestProject)

    glueContext.getSinkWithFormat(connectionType = "s3",
      options = JsonOptions(Map("path" -> medicareCols)), format = "json").writeDynamicFrame(medicareResMakeCols)

    glueContext.getSinkWithFormat(connectionType = "s3",
      options = JsonOptions(Map("path" -> medicareStruct)), format = "json").writeDynamicFrame(medicareResMakeStruct)

    glueContext.getSinkWithFormat(connectionType = "s3",
      options = JsonOptions(Map("path" -> medicareSql)), format = "json").writeDynamicFrame(medicareSqlDyf)

//    val medicareCols =
  }
}
