import org.apache.spark.{SparkConf, SparkContext}

object sortBy {
  def main(Args:Array[String]):Unit = {
    val conf = new SparkConf().setAppName("sortBy").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(1,3,5,2,8))

    val rlt = rdd.sortBy(x=>x)
    println(rlt.collect().toList) //List(1, 2, 3, 5, 8)
  }
}
