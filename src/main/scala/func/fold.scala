package func

import org.apache.spark.{SparkConf, SparkContext}

object fold {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("fold").setMaster("local")
    val sc = new SparkContext(conf)

//    val rdd = sc.parallelize(List(1, 2, 2)) //输出 9
    val rdd = sc.parallelize(List(1, 2, 2),2) //输出 11

    val rlt = rdd.fold(2)(_+_)

    println(rlt)

  }
}
