package func

import org.apache.spark.{SparkConf, SparkContext}

object aggregate {
  def main(Args:Array[String]): Unit = {

    val conf = new SparkConf().setAppName("aggregate").setMaster("local")
    val sc = new SparkContext(conf)

    val data = List(1,2,3,4,5,6,7,8,9)
    val rdd = sc.parallelize(data,2)

    val res = data.aggregate((0,0))(
      (acc,number)=>(acc._1+number, acc._2+1),
      (par1, par2) => (par1._1+par2._1, par1._2+par2._2)
    )

    println(res)  //(45,9)

  }
}
