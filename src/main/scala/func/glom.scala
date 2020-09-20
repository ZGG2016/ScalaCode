package func

import org.apache.spark.{SparkConf, SparkContext}

object glom {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("glom").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1, 2, 2, 2, 5, 6, 8, 8, 8, 8),2)

    val rlt = rdd.glom()
    /**
     * 1
     * 2
     * 2
     * 2
     * 5
     * ------------------
     * 6
     * 8
     * 8
     * 8
     * 8
     * ------------------
     */
    rlt.collect().foreach({
      x => x.foreach(println)
        println("------------------")
    })

  }
}
