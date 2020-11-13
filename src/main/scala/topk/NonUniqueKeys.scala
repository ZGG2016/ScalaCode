package topk

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.SortedMap


/**
 * topK算法：Key的值不是唯一的，根据value排序
 *
 * 这里取前5名
 */

object NonUniqueKeys {
  def main(Args:Array[String]):Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("NonUniqueKeys")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("src/main/resources/UniqueKeys.txt")

    val kvRDD = rdd.map(line => {
      val tokens = line.split(" ")
      (tokens(0), tokens(1).toInt)
    })

    //方法1
//    val createCombiner: Int => Int = (v: Int) => v
//    val mergeValue: (Int, Int) => Int = (a: Int, b: Int) => a + b
//
//    kvRDD.combineByKey(createCombiner, mergeValue, mergeValue)
//      .map(_.swap)
//      .groupByKey()
//      .sortByKey(ascending = false)
//      .take(5)
//      .foreach(println)
//kvRDD.groupByKey().map(item => item._1 -> item._2.toList.sortWith(_ > _).take(3))

    //方法2
    val UniqueKey = kvRDD.reduceByKey(_+_)

    UniqueKey.mapPartitions(iter=>{
      var sortedMap = SortedMap.empty[Int, String]
      iter.foreach(x =>{
        sortedMap += x.swap
        if (sortedMap.size > 5) {
          sortedMap = sortedMap.takeRight(5)
        }
      })
      sortedMap.takeRight(5).toIterator
    }).foreach(println)

  }

}
