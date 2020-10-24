package func

import org.apache.spark.{SparkConf, SparkContext}

object persist {
  def main(Args:Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("persist").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.parallelize(Array(("aa", 1), ("bb", 5), ("cc", 9))).persist()

    //(aa,1)
    //(bb,5)
    //(cc,9)
    rdd1.foreach(println)

    val rdd2 = sc.parallelize(Array(("aa", 1), ("bb", 5), ("cc", 9))).cache()

    //(aa,1)
    //(bb,5)
    //(cc,9)
    rdd2.foreach(println)

  }
}
