package func

import org.apache.spark.{SparkConf, SparkContext}

object flatMapValues {
  def main(Args:Array[String]): Unit = {
    val conf = new SparkConf().setAppName("mapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val data = List(("a", "tom"), ("b", "jack"),("c", "mike"),("d", "alice"))
    val rdd = sc.parallelize(data,2)
    //针对分区操作
    val rlt = rdd.flatMapValues(x=>x.toUpperCase)

    rlt.collect.foreach(println)
  }
}
