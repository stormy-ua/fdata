package com.fdata.examples

import com.fdata.core._

case class WCount(word: String, count: Int)

object WordCountJob {

  // Define word count job once. The job definition is agnostic of a specific interpreter.
  def apply(ctx: FDataContext)(implicit wcCoder: ctx.Coder[WCount]): ctx.FCollection[String] = {
    import ctx.syntax._
    import ctx.coders._

    val counts = ctx
      .parallelize("Who's there?", "I think I hear them. Stand, ho! Who's there?")
      .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .reduceByKey(_ + _)
      .map { case (k, v) => WCount(k, v) }
      .map { wc => wc.word + ":" + wc.count.toString }

    counts
      .saveAsTextFile("/Users/kirillp/tmp/word_counts")

    counts
  }

}

// Run the job defined above on Apache Flink.
object WordCountFlinkJob  {

  import org.apache.flink.api.scala._

  import com.fdata.flink._
  import org.apache.flink.api.scala._

  def main(args: Array[String]): Unit = {
    val ctx = new FlinkContext

    WordCountJob(ctx)

    ctx.run()
  }

}

// Run the job defined above on scio/Apache Beam.
object WordCountScioJob  {

  import com.fdata.scio._

  def main(args: Array[String]): Unit = {
    val ctx = new ScioFContext

    WordCountJob(ctx)

    ctx.run()
  }

}

// Run the job defined above with the mock interpreter
object WordCountMockJob  {

  import com.fdata.mock._

  def main(args: Array[String]): Unit = {
    val ctx = new MockFContext

    val counts = WordCountJob(ctx)
    System.out.println(counts)

    ctx.run()
  }

}