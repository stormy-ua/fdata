package com.fdata.flink.coders

import org.apache.flink.api.common.typeinfo.TypeInformation

import scala.reflect.ClassTag

final case class FlinkCoder[T](classTag: ClassTag[T], typeInfo: TypeInformation[T])

object FlinkCoder {

  implicit def create[T](implicit classTag: ClassTag[T], typeInfo: TypeInformation[T])
  : FlinkCoder[T] = FlinkCoder(classTag, typeInfo)
  
}