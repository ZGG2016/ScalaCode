package func

import org.apache.spark.{SparkConf, SparkContext}

object groupBy {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("groupBy").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1, 2, 2, 2, 5, 6, 8, 8, 8, 8))

    val rlt = rdd.groupBy(x=>x%2)
    //(0,CompactBuffer(2, 2, 2, 6, 8, 8, 8, 8))
    //(1,CompactBuffer(1, 5))
    rlt.collect().foreach(println)

  }
}
