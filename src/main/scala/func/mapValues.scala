package func

import org.apache.spark.{SparkConf, SparkContext}

object mapValues {
  def main(Args:Array[String]): Unit = {
    val conf = new SparkConf().setAppName("mapValues").setMaster("local")
    val sc = new SparkContext(conf)

    val data = List(("a", "tom"), ("b", "jack"),("c", "mike"),("d", "alice"))
    val rdd = sc.parallelize(data,2)
    //针对分区操作
    val rlt = rdd.mapValues(x=>x.toUpperCase)

    rlt.collect.foreach(println)
    /**
     * (a,TOM)
     * (b,JACK)
     * (c,MIKE)
     * (d,ALICE)
     *
     */
  }
}
