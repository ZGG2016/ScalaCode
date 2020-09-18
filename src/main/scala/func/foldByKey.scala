package func

import org.apache.spark.{SparkConf, SparkContext}

object foldByKey {

  def func2(index:Int,iter:Iterator[Any]):Iterator[Any]={
    iter.toList.map(x=>"分区 "+index+" 的计算结果是 "+x).iterator
  }

  def main(Args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("foldByKey").setMaster("local")
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(("a", 1), ("a", 6),("a", 7),("b", 5), ("b", 3)), 2)

    //rdd.mapPartitionsWithIndex(func2).foreach(println)

    val rlt = rdd.foldByKey(2)(_ + _) //先对每个V都加2，再对相同Key的value值相加

    println(rlt.collect().toList) //List((b,10), (a,18))

    /**
     * 分区0：("a", 1), ("a", 6)
     * 分区1：("a", 7),("b", 5), ("b", 3)
     *
     * "0值":2   【第一个参数函数：创建初始值】
     *  对分区0：
     *         2+1=3
     *         3+6=9 ("a",9)
     *  对分区1：
     *         2+5=7
     *         7+3=10 ("b",10)【第二个参数函数，追加】
     *         2+7=9  ("a",9)
     *
     *  rlt:("a",9+9=18)，即  【第二个参数函数，合并】
     *      ("b",10)
     *
     *
     * */
  }
}
