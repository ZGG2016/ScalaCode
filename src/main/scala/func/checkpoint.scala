package func

import org.apache.spark.{SparkConf, SparkContext}

object checkpoint {
  def main(Args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("checkpoint").setMaster("local")
    val sc = new SparkContext(conf)

    sc.setCheckpointDir("src/main/resources/checkpoint/")

    val rdd = sc.parallelize(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val res = rdd.map(x => x + 1).cache()
    res.checkpoint()
    res.collect().foreach(println)

  }
}
