package func

import org.apache.spark.{SparkConf, SparkContext}

object collectAsMap {
  def main(Args:Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("collect").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.parallelize(List(1,2,3))
    val rdd2 = sc.parallelize(List("a","b","c"))
    //(2,b)
    //(1,a)
    //(3,c)
    rdd1.zip(rdd2).collectAsMap.foreach(println)

  }
}
