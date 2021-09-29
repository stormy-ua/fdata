package com.fdata.examples

import com.fdata.core._

object WordCount {

  // Define word count job once. The job definition is agnostic of a specific interpreter.
  def apply(ctx: FDataContext): Unit = {
    import ctx.syntax._
    import ctx.coders._

    ctx
      .parallelize("Who's there?", "I think I hear them. Stand, ho! Who's there?")
      .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .reduceByKey(_ + _)
      .map { case (k, v) => k + ":" + v.toString }
      .saveAsTextFile("/Users/kirillp/tmp/word_counts")
  }

}

// Run the job defined above on Apache Flink.
object WordCountFlink  {

  import com.fdata.flink._
  import org.apache.flink.api.scala._

  def main(args: Array[String]): Unit = {
    val ctx = new FlinkContext

    WordCount(ctx)

    ctx.run()
  }

}

// Run the job defined above on scio/Apache Beam.
object WordCountScio  {

  import com.fdata.scio._

  def main(args: Array[String]): Unit = {
    val ctx = new ScioFContext

    WordCount(ctx)

    ctx.run()
  }

}