package com.fdata.flink

import com.fdata.core._
import com.fdata.flink.coders.FlinkCoder
import com.fdata.flink.syntax.FlinkFCollectionSyntax
import org.apache.flink.api.scala.ExecutionEnvironment

final class FlinkContext extends FDataContext {

  type FCollection[T] = FlinkFCollection[T]
  type Coder[T] = FlinkCoder[T]

  val syntax = new FlinkFCollectionSyntax

  private val ctx = ExecutionEnvironment.getExecutionEnvironment

  def parallelize[T : FlinkCoder](data: T*): FlinkFCollection[T] = {
    val coder = implicitly[FlinkCoder[T]]
    FlinkFCollection(ctx.fromElements(data: _*)(coder.classTag, coder.typeInfo))
  }

  def run(): Unit = ctx.execute()
}
