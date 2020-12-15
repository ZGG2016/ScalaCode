package sparkstreaming

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
/*
* spark streaming 直连 kafka 测试： WordCount
* standalone集群运行：
*    spark-submit --packages org.apache.spark:spark-streaming-kafka-0-10_2.12:3.0.1
*                 --class sparkstreaming.WithKafkaTest
*                 --master spark://zgg:7077
*                 wkt.jar
*
* 生产者输入：aa bb aa cc bb
* 输出：(aa,2)
*      (bb,2)
*      (cc,1)
* */
object WithKafkaTest {
  def main(Args:Array[String]):Unit = {
    val sparkConf = new SparkConf().setAppName("WithKafkaTest")
    val sc = new SparkContext(sparkConf)
    val ssc =  new StreamingContext(sc, Milliseconds(10000))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "zgg:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "1",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("withsparktest")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      locationStrategy = PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val lines = stream.map(record => record.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)

    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
