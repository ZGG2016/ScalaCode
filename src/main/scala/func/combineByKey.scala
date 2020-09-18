package func

import org.apache.spark.{SparkConf, SparkContext}

object combineByKey {
  def main(Args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("aggregateByKey").setMaster("local")

    val sc = new SparkContext(conf)

    val data = List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8))
    val rdd = sc.parallelize(data,2)

    val rlt  = rdd.combineByKey(
      (v:Int) => (v,0),
      (acc:(Int,Int),v:Int) => (acc._1+v,acc._2+v),  //要定义返回值类型
      (r1:(Int,Int),r2:(Int,Int)) => (r1._1+r2._1,r1._2+r2._2)  //这里的r就是上步骤的acc
    )
    rlt.collect().foreach(println)  //(2,(3,0))、(1,(9,6))、(3,(14,8))

  }
}
