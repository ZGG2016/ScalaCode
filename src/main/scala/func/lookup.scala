package func

import org.apache.spark.{SparkConf, SparkContext}

object lookup {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("lookup.md test").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List((1,"a"),(2,"b"),(1,"c")),2)

    val rlt = rdd.lookup(1)
    println(rlt) //WrappedArray(a, c)
  }
}
