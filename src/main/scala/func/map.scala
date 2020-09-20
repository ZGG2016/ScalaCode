package func

import org.apache.spark.{SparkConf, SparkContext}

object map {
  def main(Args:Array[String]): Unit ={
    val conf = new SparkConf().setAppName("map").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List("ab","cd"))

    val rlt1 = rdd.map(x=>x.toUpperCase)
    val rlt2 = rdd.flatMap(x=>x.toUpperCase)
    //AB
    //CD
    rlt1.collect.foreach(println)
    //A
    //B
    //C
    //D
    rlt2.collect.foreach(println)

//      val test = sc.parallelize(List(1,2,3))
//      test.map(x=>x+1)
//      test.flatMap(x=>x+1) //报错：类型不匹配

  }
}


