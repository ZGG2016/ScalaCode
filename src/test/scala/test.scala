import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object test {
  def main(Args:Array[String]):Unit = {
      val conf = new SparkConf().setAppName("mapPartitions").setMaster("local")
      val sc = new SparkContext(conf)

      val rdd = sc.parallelize(List("111 78 444 45 23 111 12 35 555 78 98 23 111 222 333 444 555"))

  }

}
