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
    /**
     * 分区 0 ： (1,3) 、(1,2)、(1,4)
     * 分区 1 ： (2,3)、(3,6) 、(3,8)
     *
     * 1.createCombiner:  (v:Int) => (v,0),  【将第一个key出现的v转换成计算规则】
     * 2.mergeValue: (acc:(Int,Int),v:Int) => (acc._1+v,acc._2+v), 【分区内计算规则】
     * 3.mergeCombiners: (r1:(Int,Int),r2:(Int,Int)) => (r1._1+r2._1,r1._2+r2._2) 【分区间计算规则】
     * 【对于第三步骤：如果不同分区有相同的key，就会执行mergeCombiners。】
     * 【如分区0的key为(1,(9,6))，分区1中也有一个key是(1,(1,1))，那么结果就是(1,(10，7))】
     *
     *
     * 对于分区0：
     *        1.         2.                                   3.
     *      (3,0)  --> (3,0)
     *        2        ((3,0),2) --> (5,2)
     *        4        ((5,2),4) --> (9,6)  --> (1,(9,6))  --> (1,(9,6))
     *
     * 对于分区1：
     *      (3,0)  --> (3,0)                --> (2,(3,0))  --> (2,(3,0))
     *      (6,0)  --> (6,0)
     *        8        ((6,0),8) --> (14,8) --> (3,(14,8)) --> (3,(14,8))
     */
  }
}
