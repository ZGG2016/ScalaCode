package func

import org.apache.spark.{SparkConf, SparkContext}

object distinct {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("distinct").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1,2,2,3,3,4,5))
    val rlt1 = rdd.distinct()
    val rlt2 = rdd.distinct(2)

    // 4、1、3、5、2
    rlt1.foreach(println)
    //4、2   |   1、3、5  两个分区
    rlt2.foreach(println)

    println(rlt1.getNumPartitions)  //1
    println(rlt2.getNumPartitions)  //2

  }
}
