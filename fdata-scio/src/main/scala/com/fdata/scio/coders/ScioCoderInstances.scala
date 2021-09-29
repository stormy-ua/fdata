package com.fdata.scio.coders

import com.fdata.core.coders.FCoderInstances
import com.spotify.scio.coders.{Coder => SCoder}

final class ScioCoderInstances extends FCoderInstances[SCoder] {

  implicit val stringCoder: SCoder[String] = SCoder.stringCoder
  implicit val intCoder: SCoder[Int] = SCoder.intCoder

  implicit def tupleCoder[T1, T2](implicit t1Coder: SCoder[T1],
                                  t2Coder: SCoder[T2]): SCoder[(T1, T2)] = SCoder.tuple2Coder
}
