package func

import org.apache.spark.{SparkConf, SparkContext}

object groupByKey {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("groupByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize("aabbaab")

    val rlt = rdd.map((_,1)).groupByKey()

    println(rlt.collect().toBuffer) //ArrayBuffer((a,CompactBuffer(1, 1, 1, 1)), (b,CompactBuffer(1, 1, 1)))
  }
}
