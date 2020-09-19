package func

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

object saveAsHadoopFile {
  def main(Args:Array[String]): Unit = {
    val conf = new SparkConf().setAppName("saveAsHadoopFile").setMaster("spark://zgg:7077")
    val sc = new SparkContext(conf)

    val data = Array(("a","2"),("a","1"),("b","5"),("b","3"),("b","9"))
    val rdd = sc.parallelize(data,2)

    val outPath = "hdfs://zgg:9000/out/saveAsHadoopFile"
    rdd.saveAsHadoopFile(outPath,classOf[Text],classOf[Text],classOf[TextOutputFormat[Text,Text]])
  }
}
