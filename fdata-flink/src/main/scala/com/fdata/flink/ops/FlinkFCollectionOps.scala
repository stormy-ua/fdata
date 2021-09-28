package com.fdata.flink.ops

import com.fdata.core._
import com.fdata.core.ops.FCollectionOps
import com.fdata.flink.FlinkFCollection
import com.fdata.flink.coders.FlinkCoder

class FlinkFCollectionOps[T](val coll: FlinkFCollection[T])
  extends FCollectionOps[FlinkFCollection, FlinkCoder, T] {

  def flatMap[R: FlinkCoder](fun: T => TraversableOnce[R]): FlinkFCollection[R] = {
    val coder = implicitly[FlinkCoder[R]]
    FlinkFCollection(coll.internal.flatMap(fun)(coder.typeInfo, coder.classTag))
  }

  def map[R: FlinkCoder](fun: T => R): FlinkFCollection[R] = {
    val coder = implicitly[FlinkCoder[R]]
    FlinkFCollection(coll.internal.map(fun)(coder.typeInfo, coder.classTag))
  }
}
