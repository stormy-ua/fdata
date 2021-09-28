package com.fdata.flink.syntax

import com.fdata.core._
import com.fdata.flink.FlinkFCollection
import com.fdata.flink.coders.FlinkCoder
import com.fdata.flink.ops._

final class FlinkFCollectionSyntax extends FCollectionSyntax[FlinkFCollection, FlinkCoder] {
  implicit def toFCollectionOps[T](coll: FlinkFCollection[T])
  : FCollectionOps[FlinkFCollection, FlinkCoder, T] = new FlinkFCollectionOps[T](coll)

  implicit def toKeyedFCollectionOps[K, V](coll: FlinkFCollection[(K, V)])
  : KeyedFCollectionOps[FlinkFCollection, FlinkCoder, K, V] =
    new FlinkKeyedFCollectionOps[K, V](coll)

  implicit def toStringFlinkFCollectionOps(coll: FlinkFCollection[String])
  : StringFCollectionOps[FlinkFCollection, FlinkCoder] = new FlinkStringFCollectionOps(coll)
}
