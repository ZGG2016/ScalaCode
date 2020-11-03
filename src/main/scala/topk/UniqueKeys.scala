package topk

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.SortedMap

/**
 * topK算法：Key的值是唯一的，根据value排序
 *
 * 这里取前5名
 */

object UniqueKeys {
  def main(Args:Array[String]):Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("UniqueKeys")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("src/main/resoures/UniqueKeys.txt")

    val kvRDD = rdd.map(line => {
      val tokens: Array[String] = line.split(" ")
      (tokens(1).toInt, tokens)
    })

    //方法1
    kvRDD.sortByKey(ascending = false)
      .take(5)
      .foreach(println)

    //方法2
    kvRDD.mapPartitions(iterator => {
        var sortedMap = SortedMap.empty[Int, Array[String]]
        iterator.foreach({ tuple => {
          sortedMap += tuple
          if (sortedMap.size > 5) {
            sortedMap = sortedMap.takeRight(5)
          }
        }
        })
        sortedMap.takeRight(5).toIterator
      }).foreach(println)

  }
}
