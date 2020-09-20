package func

import org.apache.spark.{SparkConf, SparkContext}

object zipWithIndex {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("zipWithIndex").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("a","b","c"))
    val rdd2 = sc.parallelize(List("d","e","f"),2)

    val rlt1 = rdd1.zipWithIndex();

    val rlt2 = rdd2.zipWithUniqueId();

    rlt1.collect().foreach(println) //(a,0)(b,1)(c,2)

    rlt2.collect().foreach(println) //(d,0)(e,1)(f,3)

  }
}



