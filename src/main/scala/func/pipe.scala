package func

import org.apache.spark.{SparkConf, SparkContext}

object pipe {
    def main(args: Array[String]) {
      val sparkConf = new SparkConf().setAppName("pipe Test")
      val sc = new SparkContext(sparkConf)

      val data = List("hi", "hello", "how", "are", "you")
      val dataRDD = sc.parallelize(data)

      val scriptPath = "/home/gt/spark/bin/echo.sh"
      val pipeRDD = dataRDD.pipe(scriptPath)

      print(pipeRDD.collect())
      sc.stop()

    }
  }


//#!/bin/bash
//echo "Running shell script";
//RESULT="";#变量两端不能直接接空格符
//while read LINE; do
//RESULT=${RESULT}" "${LINE}
//done
//
//echo ${RESULT} > out123.txt
//
