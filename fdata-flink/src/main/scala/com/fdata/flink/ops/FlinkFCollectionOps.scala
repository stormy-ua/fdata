package com.fdata.flink.ops

import com.fdata.core._
import com.fdata.core.ops._
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

class FlinkKeyedFCollectionOps[K, V](coll: FlinkFCollection[(K, V)])
  extends KeyedFCollectionOps[FlinkFCollection, FlinkCoder, K, V] {

  def reduceByKey(op: (V, V) => V): FlinkFCollection[(K, V)] =
    FlinkFCollection(coll.internal.groupBy(0).reduce(
      (p1: (K, V), p2: (K, V)) => p1._1 -> op(p1._2, p2._2)
    ))

}

class FlinkStringFCollectionOps(coll: FlinkFCollection[String])
  extends StringFCollectionOps[FlinkFCollection, FlinkCoder] {

  def saveAsTextFile(path: String): Unit =
    coll.internal.setParallelism(1).writeAsText(path)

}