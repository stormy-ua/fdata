package com.fdata.core.ops

trait FCollectionOps[FCollection[_], Coder[_], T] {

  def flatMap[R: Coder](fun: T => TraversableOnce[R]): FCollection[R]

  def map[R: Coder](fun: T => R): FCollection[R]

}

trait KeyedFCollectionOps[FCollection[_], Coder[_], K, V] {

  def reduceByKey(op: (V, V) => V): FCollection[(K, V)]

}

trait StringFCollectionOps[FCollection[_], Coder[_]] {

  def saveAsTextFile(path: String): Unit

}
