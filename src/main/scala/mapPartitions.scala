import org.apache.spark.{SparkConf, SparkContext}

object mapPartitions {
  def main(Args:Array[String]):Unit ={
    val conf = new SparkConf().setAppName("mapPartitions").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1,2,3,4,5,6,7,8,9),2)

    def func1(iter:Iterator[Int]):Iterator[String]={
      iter.toList.map(x=>"计算结果是 "+x).iterator
    }

    def func2(index:Int,iter:Iterator[Int]):Iterator[String]={
      iter.toList.map(x=>"分区 "+index+" 的计算结果是 "+x).iterator
    }

    rdd.mapPartitions(func1).foreach(println)
    println("----------------------------------------------")
    rdd.mapPartitionsWithIndex(func2).foreach(println)
  }
}

/**
 * mapPartitions 和 mapPartitionsWithIndex 区别：
 *      mapPartitionsWithIndex的输入函数多了一个 分区的index 参数，所以可以取出分区编号。
 *
 * */