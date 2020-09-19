package func

import org.apache.spark.{SparkConf, SparkContext}

object countByKey {
  def main(Args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("countByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(("a", 1), ("a", 6),("a", 7),("b", 5), ("b", 3)), 2)

    val rlt = rdd.countByKey()  //本身就会collect到driver

    println(rlt)  //Map(b -> 2, a -> 3)
  }
}
