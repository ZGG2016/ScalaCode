import org.apache.spark.{SparkConf, SparkContext}

object wc_local {
  def main(Args:Array[String]):Unit = {

    //本地调试
    val conf = new SparkConf().setAppName("wordcount").setMaster("local")
    val sc = new SparkContext(conf)

    val data = sc.textFile("src/main/resources/wc.txt")

    val wc = data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    println("result is:")
    wc.collect().foreach(println)

    sc.stop()
  }
}





