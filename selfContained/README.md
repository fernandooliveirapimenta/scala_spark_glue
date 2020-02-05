export JAVA_OPTS="$JAVA_OPTS -Dhttp.proxyHost=proxywcg.bbmapfre.corp -Dhttp.proxyPort=8080 -Dhttp.proxyUser=fosilva -Dhttp.proxyPassword=Fefe@123456 -Dhttps.proxyHost=proxywcg.bbmapfre.corp -Dhttps.proxyPort=8080 -Dhttps.proxyUser=fosilva -Dhttps.proxyPassword=Fefe@123456" 

sbt package

YOUR_SPARK_HOME/bin/spark-submit \
  --class "SimpleApp" \
  --master local[4] \
  target/scala-2.12/simple-project_2.12-1.0.jar

spark-submit --class "SelfContained" --master local[4] target/scala-2.12/selfcontained_2.12-1.0.jar
