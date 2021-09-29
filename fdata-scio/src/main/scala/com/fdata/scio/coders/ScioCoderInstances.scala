package com.fdata.scio.coders

import com.fdata.core.coders.FCoderInstances
import com.spotify.scio.coders.{Coder => SCoder}

final class ScioCoderInstances extends FCoderInstances[SCoder] {

  implicit val stringCoder: SCoder[String] = SCoder[String]
  implicit val intCoder: SCoder[Int] = SCoder[Int]

  implicit def tupleCoder[T1, T2](implicit t1Coder: SCoder[T1],
                                  t2Coder: SCoder[T2]): SCoder[(T1, T2)] = SCoder[(T1, T2)]
}
