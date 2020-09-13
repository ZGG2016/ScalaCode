import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object test {
  def main(Args:Array[String]):Unit = {
      val conf = new SparkConf().setAppName("mapPartitions").setMaster("local")
      val sc = new SparkContext(conf)

      val data = sc.parallelize(1 to 5)

      val result = data.mapPartitions(x=>{
        val list = new ListBuffer[String]()
        while(x.hasNext){
          list+=x.next()+"==>"
        }
        list.iterator
      })

      result.foreach(println)
    }

}
