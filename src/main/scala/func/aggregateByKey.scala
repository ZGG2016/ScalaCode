package func

import org.apache.spark.{SparkConf, SparkContext}

object aggregateByKey {

  def func2(index:Int,iter:Iterator[Any]):Iterator[Any]={
    iter.toList.map(x=>"分区 "+index+" 的计算结果是 "+x).iterator
  }

  def main(Args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("aggregateByKey").setMaster("local")

    val sc = new SparkContext(conf)

    val data = List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8))
    val rdd = sc.parallelize(data,2)

    //rdd.mapPartitionsWithIndex(func2).foreach(println)

    val rlt  = rdd.aggregateByKey(0)(math.max, _+_)
    rlt.collect.foreach(println)

  }
}
