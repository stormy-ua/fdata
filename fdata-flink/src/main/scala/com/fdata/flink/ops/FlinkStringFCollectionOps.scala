package com.fdata.flink.ops

import com.fdata.core.StringFCollectionOps
import com.fdata.flink.FlinkFCollection
import com.fdata.flink.coders.FlinkCoder

class FlinkStringFCollectionOps(coll: FlinkFCollection[String])
  extends StringFCollectionOps[FlinkFCollection, FlinkCoder] {

  def saveAsTextFile(path: String): Unit =
    coll.internal.setParallelism(1).writeAsText(path)

}