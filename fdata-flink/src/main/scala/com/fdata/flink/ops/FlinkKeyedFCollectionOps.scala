package com.fdata.flink.ops

import com.fdata.core._
import com.fdata.flink.FlinkFCollection
import com.fdata.flink.coders.FlinkCoder

class FlinkKeyedFCollectionOps[K, V](coll: FlinkFCollection[(K, V)])
  extends KeyedFCollectionOps[FlinkFCollection, FlinkCoder, K, V] {

  def reduceByKey(op: (V, V) => V): FlinkFCollection[(K, V)] =
    FlinkFCollection(coll.internal.groupBy(0).reduce(
      (p1: (K, V), p2: (K, V)) => p1._1 -> op(p1._2, p2._2)
    ))

}
