package com.fdata.flink

import org.apache.flink.api.scala.DataSet

final case class FlinkFCollection[T](internal: DataSet[T])
