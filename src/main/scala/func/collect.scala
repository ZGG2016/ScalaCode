package func

import org.apache.spark.{SparkConf, SparkContext}

object collect {
  def main(Args:Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("collect").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.parallelize(0 to 2)

    val f : PartialFunction[Int,String] = {case 0 => "aa"
    case 1 => "bb"
    case 2 => "cc"
    case _ => "0"
    }
    //aa
    //bb
    //cc
    rdd.collect(f).foreach(println)
  }
}
