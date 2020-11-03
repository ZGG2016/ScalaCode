package topk

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * topK算法：同一分组Key中取出TopN的数据。
 */
object group {
  def main(Args:Array[String]):Unit = {

    val conf = new SparkConf().setMaster("local").setAppName("NonUniqueKeys")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("src/main/resoures/topk.txt")

    val kvRDD = rdd.flatMap(_.split(" ")).map(x => (x.toInt, 1))

    //方法1
    //使用groupByKey的方式实现读取TopK的数据
    kvRDD.groupByKey().map(x => {
      val topk = x._2.toList.sorted.takeRight(5).reverse
      (x._1, topk)
    }).foreach(println)

    //方法2
    //2.使用两阶段聚合，先使用随机数进行分组聚合取出局部TopK,再聚合取出全局TopK的数据
    kvRDD.mapPartitions(iterator => {
        iterator.map(x => {
          ((Random.nextInt(10), x._1), x._2)
        })
      }).groupByKey().flatMap({
      //获取values中的前N个值 ，并返回topN的集合数据
      case ((_, key), values) =>
        values.toList.sorted.takeRight(5).map(value => (key, value))
    }).groupByKey().map(tuple2 => {
      val topn = tuple2._2.toList.sorted.takeRight(5).reverse
      (tuple2._1, topn)
    }).foreach(println)

    //方法3
    //使用aggregateByKey获取TopK的记录
    kvRDD.aggregateByKey(ArrayBuffer[Int]())(
      (u, v) => {
        u += v
        u.sorted.takeRight(5)
      },
      (u1, u2) => {
        //对任意的两个局部聚合值进行聚合操作，可以会发生在combiner阶段和shuffle之后的最终的数据聚合的阶段
        u1 ++= u2
        u1.sorted.takeRight(5)
      }
    ).map(tuple2 => (tuple2._1, tuple2._2.toList.reverse)).foreach(println)
  }
}

//详细解释：https://blog.csdn.net/luofazha2012/article/details/80636858