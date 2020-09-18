package func

import org.apache.spark.{SparkConf, SparkContext}

object mapPartitions {

  def func1(iter:Iterator[Int]):Iterator[String]={
    iter.toList.map(x=>"计算结果是 "+x).iterator
  }

  def func2(index:Int,iter:Iterator[Int]):Iterator[String]={
    iter.toList.map(x=>"分区 "+index+" 的计算结果是 "+x).iterator
  }

  def main(Args:Array[String]):Unit ={
    val conf = new SparkConf().setAppName("mapPartitions").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1,2,3,4,5,6,7,8,9),2)

    rdd.mapPartitions(func1).foreach(println)
    println("----------------------------------------------")
    rdd.mapPartitionsWithIndex(func2).foreach(println)
  }
}
