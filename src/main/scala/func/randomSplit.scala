package func

import org.apache.spark.{SparkConf, SparkContext}

object randomSplit {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("randomSplit").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1, 2, 2, 2, 5, 6, 8, 8, 8, 8))

    val rlt = rdd.randomSplit(Array(1.0,2.0,3.0,4.0),10)

    println(rlt.length)  //4  ，返回值类型：Array[RDD[T]]

    for(x <- rlt){
      x.collect().foreach(print) //2  |8  | 1568  | 2288  |
      println()
      println("---------------------")
    }

  }
}


