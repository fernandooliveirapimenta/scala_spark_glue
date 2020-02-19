from pyspark import SparkContext
sc = SparkContext(appName="getEvenNums")
x = sc.parallelize([1, 2, 3, 4])
y = x.filter(lambda x: (x % 2 == 0))
print(y.collect())
sc.stop()
