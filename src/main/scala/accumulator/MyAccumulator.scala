package accumulator

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 自定义累加器：统计卡种数量。
 */

case class Card(var card1Count: Int, var card2Count: Int)

class MyAccumulator extends AccumulatorV2[Card, Card]{

  var result = new Card(0, 0)

  override def isZero: Boolean = {
    result.card1Count == 0 && result.card2Count == 0
  }

  override def copy(): AccumulatorV2[Card, Card] = {
    val newCalcCardCount = new MyAccumulator()
    newCalcCardCount.result = this.result
    newCalcCardCount
  }

  override def reset(): Unit = {
    result.card1Count = 0
    result.card2Count = 0
  }

  override def add(v: Card): Unit = {
    result.card1Count += v.card1Count
    result.card2Count += v.card2Count
  }

  override def merge(other: AccumulatorV2[Card, Card]): Unit = other match {
    case o: MyAccumulator =>
      result.card1Count += o.result.card1Count
      result.card2Count += o.result.card2Count
  }

  override def value: Card = result
}

object CardCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("calcCardCountDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val cc = new MyAccumulator
    sc.register(cc)
    val cardList = sc.parallelize(List[String]("card1 1", "card1 3", "card1 7", "card2 5", "card2 2"), 2)
    val cardMapRDD = cardList.map(card => {
      var cardInfo = new Card(0, 0)
      card.split(" ")(0) match {
        case "card1" => cardInfo = Card(card.split(" ")(1).toInt, 0)
        case "card2" => cardInfo = Card(0, card.split(" ")(1).toInt)
        case _ => Card(0, 0)
      }
      cc.add(cardInfo)
    })
    cardMapRDD.count() //执行action，触发上面的累加操作
    //card1总数量为:11,card2总数量为:7
    println("card1总数量为:" + cc.result.card1Count + ",card2总数量为:" + cc.result.card2Count)
  }
}

//原文链接:https://www.cnblogs.com/shun7man/p/12764840.html