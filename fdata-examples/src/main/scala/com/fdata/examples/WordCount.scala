package com.fdata.examples

import com.fdata.core._
import com.fdata.flink._
import org.apache.flink.api.scala._

object WordCount {

  private def job(ctx: FDataContext)(
    implicit scoder: ctx.Coder[String], sicoder: ctx.Coder[(String, Int)]): Unit = {
    import ctx.syntax._

    ctx
      .parallelize("Who's there?", "I think I hear them. Stand, ho! Who's there?")
      .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .reduceByKey(_ + _)
      .map { case (k, v) => k + ":" + v.toString }
      .saveAsTextFile("/Users/kirillp/tmp/flink_counts.txt")
  }

  def main(args: Array[String]): Unit = {
    val ctx = new FlinkContext

    job(ctx)

    ctx.run()
  }

}
