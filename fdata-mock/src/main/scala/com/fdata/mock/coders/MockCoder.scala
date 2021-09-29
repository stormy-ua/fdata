package com.fdata.mock.coders

import com.fdata.core.coders.FCoderInstances

final class MockCoder[T]

object MockCoder {

  implicit def apply[T]: MockCoder[T] = new MockCoder[T]

}

object MockCoderInstances extends FCoderInstances[MockCoder] {
  implicit val stringCoder: MockCoder[String] = MockCoder.apply
  implicit val intCoder: MockCoder[Int] = MockCoder.apply

  implicit def tupleCoder[T1, T2](implicit t1Coder: MockCoder[T1], t2Coder: MockCoder[T2])
  : MockCoder[(T1, T2)] = MockCoder.apply
}