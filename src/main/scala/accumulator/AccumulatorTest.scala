package accumulator

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 累加器简单使用：统计有多少个偶数
 */

object AccumulatorTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local")
    val sc = new SparkContext(conf)

    val accum= sc.longAccumulator("accumulator")

    val rdd = sc.parallelize(1 to 10)

    val newData = rdd.map{x => {
      if(x%2 == 0){
        accum.add(1)   //使用add加
        0
      }else 1
    }}

    newData.foreach(print)  //1010101010
    println("accum.sum:"+accum.sum)  //accum.sum:5
    println("accum.count:"+accum.count)  //accum.count:5
    println("accum.avg:"+accum.avg)  //accum.avg:1.0
    println("accum.value:"+accum.value)  //accum.value:5


    newData.count //action操作count又触发一次
    println("first:"+accum.value)  //first:10
    newData.foreach(print)  //action操作foreach又触发一次   1010101010
    println("second:"+accum.value)  //second:15

  }
}
