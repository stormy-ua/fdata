package com.fdata.core.coders

trait FCoderInstances[Coder[_]] {

  implicit val stringCoder: Coder[String]
  implicit val intCoder: Coder[Int]

  implicit def tupleCoder[T1, T2](implicit t1Coder: Coder[T1], t2Coder: Coder[T2]): Coder[(T1, T2)]
}
