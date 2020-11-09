package sparkstreaming

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object NetworkWC {
  def main(Args:Array[String]):Unit = {
    if (Args.length < 2) {
      System.err.println("Usage: NetworkWordCount <hostname> <port>")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf,Seconds(1))

    val lines = ssc.socketTextStream(Args(0),Args(1).toInt,StorageLevel.MEMORY_AND_DISK_SER)

    val wc = lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)

    wc.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
