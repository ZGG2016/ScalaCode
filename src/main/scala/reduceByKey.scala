import org.apache.spark.{SparkConf, SparkContext}

object reduceByKey {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("reduceByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("src/main/data/reduceByKey.txt")

    //val pairs = lines.map(s=>(s,1))
    val pairs = lines.map((_,1))

    //val count = pairs.reduceByKey((a,b)=>a+b)
    val count = pairs.reduceByKey(_+_)
    println(count.collect().toBuffer)
  }
}
