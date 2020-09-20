import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object test {
  def main(Args:Array[String]):Unit = {
      val conf = new SparkConf().setAppName("mapPartitions").setMaster("local")
      val sc = new SparkContext(conf)

      val data = sc.parallelize(List(1,2,2,3,3,4,5))

      val rlt = data.map(x=>(x,null)).reduceByKey((x,y)=>x)

      rlt.foreach(println)
    }

}
