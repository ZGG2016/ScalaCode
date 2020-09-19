package func

import org.apache.spark.{SparkConf, SparkContext}

object countByValue {
  def main(Args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("countByValue").setMaster("local")
    val sc = new SparkContext(conf)

//    val rdd = sc.parallelize(List(("a", 1), ("a", 1),("a", 7),("b", 2), ("b", 3)), 2)
    val rdd = sc.parallelize(List(1,1,2,2,2,1,4,5))

//    val rlt = rdd.map(value => (value, null))
//    rlt.collect().foreach(println)  //((a,1),null)、((a,6),null)、((a,7),null)、((b,1),null)、((b,3),null)

    val rlt = rdd.countByValue()  //本身就会collect到driver

    // Map((b,2) -> 1, (a,7) -> 1, (b,3) -> 1, (a,1) -> 2)  有排序
    // Map(4 -> 1, 1 -> 3, 5 -> 1, 2 -> 3)
    println(rlt)

  }
}
