package func

import org.apache.spark.{SparkConf, SparkContext}

object repartition {
  def main(Args:Array[String]): Unit = {
    val conf = new SparkConf().setAppName("repartition").setMaster("local")
    val sc = new SparkContext(conf)

    val data = sc.textFile("src/main/data/coalesce.txt",10)

    val rlt1 = data.repartition(4)
    val rlt2 = data.repartition(100)

    println(data.partitions.length) //10
    println(rlt1.partitions.length) //4
    println(rlt2.partitions.length) //100
  }
}
