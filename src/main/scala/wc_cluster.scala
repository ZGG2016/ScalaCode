import org.apache.spark.{SparkConf, SparkContext}

object wc_cluster {
  def main(Args:Array[String]):Unit = {

    val conf = new SparkConf().setAppName("wordcount")
    val sc = new SparkContext(conf)

    val data = sc.textFile(Args(0))

    val wc = data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    println("result is:")
    wc.collect().foreach(println)

    sc.stop()
  }
}

/*
* standalone集群运行：
*     spark-submit --class wc --master spark://zgg:7077 wc.jar hdfs://zgg:9000/in/wc.txt
*
* */