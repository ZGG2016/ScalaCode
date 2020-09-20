package func

import org.apache.spark.{SparkConf, SparkContext}

object zip {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("zip").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List(1, 2, 3,4),2)
    val rdd2 = sc.parallelize(List(5, 6, 7, 8),2)


    val rlt = rdd1.zip(rdd2)
    rlt.collect().foreach(println) //(1,5)、(2,6)、(3,7)、(4,8)

  }
}
