package com.fdata.core.ops

trait FCollectionOps[FCollection[_], Coder[_], T] {

  def flatMap[R: Coder](fun: T => TraversableOnce[R]): FCollection[R]

  def map[R: Coder](fun: T => R): FCollection[R]

}
