import org.apache.spark.{SparkConf, SparkContext}

object accumulator {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("accumulator").setMaster("local")
    val sc = new SparkContext(conf)

    val accum= sc.longAccumulator("accumulator")

    val rdd = sc.parallelize(1 to 10)

    val newData = rdd.map{x => {
      if(x%2 == 0){
        accum.add(1)   //使用add加
        0
      }else 1
    }}

    newData.count

    println("first:"+accum.value)  //5

    newData.foreach(println)

    println("second:"+accum.value)  //10
  }
}
