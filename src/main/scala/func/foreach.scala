package func

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object foreach {
  def main(Args:Array[String]): Unit = {
    val conf = new SparkConf().setAppName("foreach").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd1 = sc.parallelize(List("ab", "cd"), 2)
    val rdd2 = sc.parallelize(List("ab", "cd"), 2)

    //ab
    //cd
    rdd1.foreach(x=>println(x))

    //ab
    //cd
    rdd2.foreachPartition(x=>{
      x.foreach(println)
    })
  }
}
