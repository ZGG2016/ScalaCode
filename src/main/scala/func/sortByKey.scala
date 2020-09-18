package func

import org.apache.spark.{SparkConf, SparkContext}

object sortByKey {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("sortByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val a = sc.parallelize(List("a", "b", "c", "d", "e"))
    val b = sc. parallelize (1 to a.count.toInt )
    val c = a.zip(b)
    val rlt = c.sortByKey()
    println(rlt.collect().toList) //List((a,1), (b,2), (c,3), (d,4), (e,5))
  }
}
