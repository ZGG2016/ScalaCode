package func

import org.apache.spark.{SparkConf, SparkContext}

object filter {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("filter").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1,2,3,4,5))
    val rlt = rdd.filter(_>4)

    rlt.foreach(println)
  }
}
