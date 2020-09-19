package func

import org.apache.spark.{SparkConf, SparkContext}

object reduce {
  def main(Args:Array[String]):Unit={
    val conf = new SparkConf().setAppName("reduce").setMaster("local")
    val sc = new SparkContext(conf)

    val data = List(1,2,3,4)

    val rdd = sc.parallelize(data,2)

    val rlt = rdd.reduce((a,b)=>a+b)
    println(rlt) //10

  }
}
