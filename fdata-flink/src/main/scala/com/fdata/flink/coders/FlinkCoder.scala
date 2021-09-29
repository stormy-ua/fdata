package com.fdata.flink.coders

import com.fdata.core.coders.FCoderInstances
import org.apache.flink.api.common.typeinfo.TypeInformation

import scala.reflect.ClassTag

final class FlinkCoder[T](val classTag: ClassTag[T], val typeInfo: TypeInformation[T])

object FlinkCoder {

  implicit def apply[T](implicit classTag: ClassTag[T], typeInfo: TypeInformation[T])
  : FlinkCoder[T] = new FlinkCoder(classTag, typeInfo)
  
}

final class FlinkCoderInstances extends FCoderInstances[FlinkCoder] {

  import org.apache.flink.api.scala._

  implicit val stringCoder: FlinkCoder[String] = FlinkCoder[String]
  implicit val intCoder: FlinkCoder[Int] = FlinkCoder[Int]

  implicit def tupleCoder[T1, T2](implicit t1Coder: FlinkCoder[T1],
                                  t2Coder: FlinkCoder[T2]): FlinkCoder[(T1, T2)] = {
    implicit val ti1 = t1Coder.typeInfo
    implicit val ti2 = t2Coder.typeInfo
    new FlinkCoder(implicitly[ClassTag[(T1, T2)]], implicitly[TypeInformation[(T1, T2)]])
  }
}